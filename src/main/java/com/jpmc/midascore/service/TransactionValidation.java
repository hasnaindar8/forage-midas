package com.jpmc.midascore.service;

import com.jpmc.midascore.entity.TransactionRecord;
import com.jpmc.midascore.entity.UserRecord;
import com.jpmc.midascore.foundation.Transaction;
import com.jpmc.midascore.repository.TransactionRecordRepository;
import com.jpmc.midascore.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionValidation {

    private final UserRepository userRepository;

    private final TransactionRecordRepository transactionRecordRepository;

    public TransactionValidation(UserRepository userRepository, TransactionRecordRepository transactionRecordRepository) {
        this.userRepository = userRepository;
        this.transactionRecordRepository = transactionRecordRepository;
    }

    public boolean validateTransaction(Transaction transaction) {
        Optional<UserRecord> recipientUserRecordOptional = userRepository.findById(transaction.getRecipientId());
        if (recipientUserRecordOptional.isPresent()) {
            Optional<UserRecord> senderUserRecordOptional = userRepository.findById(transaction.getSenderId());
            if (senderUserRecordOptional.isPresent()) {
                UserRecord senderUserRecord = senderUserRecordOptional.get();
                UserRecord recipientUserRecord = recipientUserRecordOptional.get();
                if (senderUserRecord.getBalance() >= transaction.getAmount()) {
                    updateUsersBalance(senderUserRecord, recipientUserRecord, transaction.getAmount());
                    return true;
                }
            }
        }
        return false;
    }

    private void updateUsersBalance(UserRecord senderUserRecord, UserRecord recipientUserRecord, float amount) {
        senderUserRecord.setBalance(senderUserRecord.getBalance() - amount);
        userRepository.save(senderUserRecord);
        recipientUserRecord.setBalance(recipientUserRecord.getBalance() + amount);
        userRepository.save(recipientUserRecord);
        saveTransaction(senderUserRecord, recipientUserRecord, amount);
    }

    private void saveTransaction(UserRecord senderUserRecord, UserRecord recipientUserRecord, float amount) {
        transactionRecordRepository.save(new TransactionRecord(senderUserRecord, recipientUserRecord, amount));
    }
}
