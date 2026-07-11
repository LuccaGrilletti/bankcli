package com.lucca.bankcli.exception;

/** Thrown when a CPF is null/blank or fails the check-digit validation in {@link com.lucca.bankcli.util.CpfValidator}. */
public class InvalidCpfException extends BankCliException {
    public InvalidCpfException(String message) {
        super(message);
    }
}
