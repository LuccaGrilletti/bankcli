package com.lucca.bankcli.exception;

public class InvalidBalanceException extends BankCliException {
    public InvalidBalanceException(String message) {
        super(message);
    }
}
