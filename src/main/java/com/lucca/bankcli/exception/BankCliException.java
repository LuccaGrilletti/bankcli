package com.lucca.bankcli.exception;

/** Base class for every checked-by-convention domain error raised by BankCLI. */
public class BankCliException extends RuntimeException {
    public BankCliException(String message) {
        super(message);
    }
}
