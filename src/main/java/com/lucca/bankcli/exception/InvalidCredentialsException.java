package com.lucca.bankcli.exception;

/** Reserved for the not-yet-implemented login feature; currently never thrown. */
public class InvalidCredentialsException extends BankCliException {
    public InvalidCredentialsException(String message) {
        super(message);
    }
}
