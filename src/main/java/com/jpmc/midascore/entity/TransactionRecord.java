package com.jpmc.midascore.entity;

import jakarta.persistence.*;

@Entity
public class TransactionRecord {

    @Id
    @GeneratedValue()
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", nullable = false)
    private UserRecord senderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipient_id", nullable = false)
    private UserRecord recipientId;

    @Column(nullable = false)
    private float amount;

    protected TransactionRecord() {
    }

    public TransactionRecord(UserRecord senderId, UserRecord recipientId, float amount) {
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "TransactionRecord{" +
                "id=" + id +
                ", senderId=" + senderId +
                ", recipientId=" + recipientId +
                ", amount=" + amount +
                '}';
    }

    public UserRecord getSenderId() {
        return senderId;
    }

    public UserRecord getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(UserRecord recipientId) {
        this.recipientId = recipientId;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
