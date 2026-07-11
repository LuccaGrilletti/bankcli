package com.lucca.bankcli.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import com.lucca.bankcli.exception.*;

/**
 * Represents a bank account.
 * Holds only data and simple validation rules tied to the account's own
 * state — more complex business rules (transfers, daily limits) belong in
 * the service layer, not here.
 */

public class Account {

    private final String id;
    private final String clientId;
    private BigDecimal balance;
    private final LocalDateTime createdAt;

    /**
     * Creates a new account with the given opening balance for the given client.
     *
     * @throws InvalidDataException if {@code clientId} is null/empty or {@code initialBalance} is null or negative
     */
    public Account(BigDecimal initialBalance, String clientId) {

        if (clientId == null || clientId.isEmpty()) {
            throw new InvalidDataException("Client ID cannot be null or empty");
        }

        if (initialBalance == null || initialBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidDataException("initialBalance cannot be negative");
        }

        this.id = UUID.randomUUID().toString();
        this.balance = initialBalance;
        this.createdAt = LocalDateTime.now();
        this.clientId = clientId;
    }

    public String getAccountId() {
        return id;
    }

    public String getClientId() {
        return clientId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Adds {@code amount} to the balance.
     *
     * @throws InvalidDataException if {@code amount} is null or not positive
     */
    public void deposit(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidDataException("Amount cannot be negative or 0");
        }
        this.balance = balance.add(amount);
    }

    /**
     * Subtracts {@code amount} from the balance.
     *
     * @throws InvalidDataException if {@code amount} is null or not positive
     * @throws InsufficientFundsException if {@code amount} is greater than the current balance
     */
    public void withdraw(BigDecimal amount) {

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidDataException("Amount cannot be negative or 0");
        }

        if (amount.compareTo(this.balance) > 0) {
            throw new InsufficientFundsException("Insufficient funds!");
        }

        this.balance = balance.subtract(amount);
    }

    @Override
    public String toString() {
        return "Account{id='%s', clientId='%s', balance='%s', createdAt=%s}".formatted(id, clientId, balance, createdAt);
    }
}
