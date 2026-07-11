package com.lucca.bankcli.exception;

/** Reserved for the not-yet-implemented transaction-history feature; currently never thrown. */
public class TransactionNotFoundException extends BankCliException {
    public TransactionNotFoundException(String message) {
        super(message);
    }
}
