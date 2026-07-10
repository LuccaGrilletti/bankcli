package com.lucca.bankcli.exception;

public class InsufficientFundsException extends BankCliException {
    public InsufficientFundsException(String message) {
        super(message);
    }
}
