package com.lucca.bankcli.model;

import com.lucca.bankcli.exception.NullRecieverIdException;
import com.lucca.bankcli.exception.NullSenderIdException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/** Placeholder reserved for a future transaction/history record (deposits, withdrawals, transfers). Not implemented yet. */
public class Transaction {
    private final String transactionId;
    private final BigDecimal amount;
    private final LocalDateTime createdAt;
    private final String senderId;
    private final String receiverId;

    /**
     * Creates a new Transaction in between two accounts with valid status and balance
     *
     * @throws IllegalArgumentException if {@code amount} is null or negative
     * @throws NullRecieverIdException if {@code senderId} is not given
     * @throws NullSenderIdException if {@code recieverId} is not given
     * */

    public Transaction(BigDecimal amount, String senderId, String receiverId) {

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }

        if (senderId == null || senderId.isEmpty()) {
            throw new NullSenderIdException("Sender Id cannot be null or empty");
        }

        if  (receiverId == null || receiverId.isEmpty()) {
            throw new NullRecieverIdException("Receiver Id cannot be null or empty");
        }

        this.transactionId = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
        this.amount = amount;
        this.senderId = senderId;
        this.receiverId = receiverId;

    }

    public String getTransactionId() {
        return transactionId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getSenderId() {
        return senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

}
