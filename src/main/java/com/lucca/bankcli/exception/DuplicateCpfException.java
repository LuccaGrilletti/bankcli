package com.lucca.bankcli.exception;

/** Thrown when registering a client whose CPF is already in use by another client. */
public class DuplicateCpfException extends BankCliException {
    public DuplicateCpfException(String message) {
        super(message);
    }
}
