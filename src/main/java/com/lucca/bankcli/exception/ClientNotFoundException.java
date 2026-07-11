package com.lucca.bankcli.exception;

/** Thrown when no client matches the requested ID or CPF. */
public class ClientNotFoundException extends BankCliException {
    public ClientNotFoundException(String message) {
        super(message);
    }
}
