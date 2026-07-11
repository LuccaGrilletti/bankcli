package com.lucca.bankcli.exception;

/** Reserved for the not-yet-implemented daily withdrawal/transfer limit rule; currently never thrown. */
public class DailyLimitExceededException extends BankCliException {
    public DailyLimitExceededException(String message) {
        super(message);
    }
}
