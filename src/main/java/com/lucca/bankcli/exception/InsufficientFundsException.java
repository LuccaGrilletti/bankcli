package com.lucca.bankcli.exception;

/** Thrown when a withdrawal amount exceeds the account's current balance. */
public class InsufficientFundsException extends BankCliException {
    public InsufficientFundsException(String message) {
        super(message);
    }
}
