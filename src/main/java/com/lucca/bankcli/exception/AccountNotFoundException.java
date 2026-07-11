package com.lucca.bankcli.exception;

/** Thrown when no account matches the requested ID. */
public class AccountNotFoundException extends BankCliException {
    public AccountNotFoundException(String message) {
        super(message);
    }
}
