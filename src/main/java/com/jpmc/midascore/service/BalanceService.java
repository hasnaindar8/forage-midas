package com.jpmc.midascore.service;

import com.jpmc.midascore.entity.UserRecord;
import com.jpmc.midascore.foundation.Balance;
import com.jpmc.midascore.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BalanceService {
    private final UserRepository userRepository;

    public BalanceService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Balance getUserBalance(long userId) {
        Optional<UserRecord> userRecordOptional = userRepository.findById(userId);
        if (userRecordOptional.isPresent()) {
            UserRecord userRecord = userRecordOptional.get();
            return new Balance(userRecord.getBalance());
        } else {
            return new Balance(0);
        }
    }
}
