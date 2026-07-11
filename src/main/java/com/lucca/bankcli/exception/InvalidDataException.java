package com.lucca.bankcli.exception;

/** Thrown for generic invalid domain input, e.g. blank required fields or a non-positive amount. */
public class InvalidDataException extends BankCliException {
    public InvalidDataException(String message) {
        super(message);
    }
}
