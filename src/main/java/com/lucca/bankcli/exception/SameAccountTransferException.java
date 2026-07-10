package com.lucca.bankcli.exception;

public class SameAccountTransferException extends BankCliException {
    public SameAccountTransferException(String message) {
        super(message);
    }
}