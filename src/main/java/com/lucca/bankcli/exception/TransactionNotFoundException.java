package com.lucca.bankcli.exception;

public class TransactionNotFoundException extends BankCliException {
    public TransactionNotFoundException(String message) {
        super(message);
    }
}