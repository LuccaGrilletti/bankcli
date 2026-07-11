package com.lucca.bankcli.exception;

/** Reserved for the not-yet-implemented transfer-between-accounts feature, for when source and destination are the same account; currently never thrown. */
public class SameAccountTransferException extends BankCliException {
    public SameAccountTransferException(String message) {
        super(message);
    }
}
