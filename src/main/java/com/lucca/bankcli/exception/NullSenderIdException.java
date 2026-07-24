package com.lucca.bankcli.exception;

public class NullSenderIdException extends BankCliException {
    public NullSenderIdException(String message) {
        super(message);
    }
}