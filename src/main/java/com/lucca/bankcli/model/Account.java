package com.lucca.bankcli.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.lucca.bankcli.exception.InvalidDataException;
import com.lucca.bankcli.util.CpfValidator;

/**
 * Representa uma conta bancária
 * Contém apenas dados e regras de validação simples ligadas ao próprio estado
 * da conta — regras de negócio mais complexas (transferência, limites diários)
 * ficam na camada de service, não aqui.
 */

public class Account {

    private final String id;
    private final String ownerName;
    private final String clientID;
    private BigDecimal balance;
    private final LocalDateTime createdAt;
    private final String cpf;

    public Account(String ownerName, BigDecimal initialBalance, String cpf) {

        if (!CpfValidator.isValid(cpf)) {
            throw new IllegalArgumentException("Invalid CPF");
        }

        if (ownerName == null || ownerName.isBlank()) {
            throw new IllegalArgumentException("OwnerName cannot be null or blank");
        }

        if (initialBalance == null || initialBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("initialBalance cannot be negative");
        }

        this.id = UUID.randomUUID().toString();
        this.ownerName = ownerName;
        this.balance = initialBalance;
        this.createdAt = LocalDateTime.now();
        this.cpf = cpf;


        //this.clientID = ;
    }

    public String getAccountId() {
        return id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getCpf() {
        return cpf;
    }

    public void deposit(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        this.balance = balance.add(amount);
    }

    public void withdraw(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }

        if (balance.compareTo(amount) > 0) {
            throw new IllegalStateException("Balance cannot be greater than current balance");
        }

        this.balance = balance.subtract(amount);
    }

    @Override
    public String toString() {
        return "Account{id='%s', ownerName='%s', balance='%s', createdAt=%s}'}".formatted(id, ownerName, balance, createdAt);
    }
}

