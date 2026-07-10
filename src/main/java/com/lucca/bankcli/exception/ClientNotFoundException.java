package com.lucca.bankcli.exception;

public class ClientNotFoundException extends BankCliException {
    public ClientNotFoundException(String message) {
        super(message);
    }
}
