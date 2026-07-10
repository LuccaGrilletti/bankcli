package com.lucca.bankcli.exception;

public class InvalidCredentialsException extends BankCliException {
    public InvalidCredentialsException(String message) {
        super(message);
    }
}