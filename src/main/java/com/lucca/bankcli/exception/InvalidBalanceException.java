package com.lucca.bankcli.exception;

/** Thrown when deleting an account or client whose account(s) still hold a non-zero balance. */
public class InvalidBalanceException extends BankCliException {
    public InvalidBalanceException(String message) {
        super(message);
    }
}
