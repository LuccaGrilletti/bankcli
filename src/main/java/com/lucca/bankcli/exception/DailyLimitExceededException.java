package com.lucca.bankcli.exception;

public class DailyLimitExceededException extends BankCliException {
    public DailyLimitExceededException(String message) {
        super(message);
    }
}