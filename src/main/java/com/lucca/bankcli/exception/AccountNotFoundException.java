package com.lucca.bankcli.exception;

public class AccountNotFoundException extends BankCliException {
    public AccountNotFoundException(String message) {
        super(message);
    }
}
