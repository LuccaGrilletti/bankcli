package com.lucca.bankcli.model;

import java.util.UUID;

import com.lucca.bankcli.exception.*;
import com.lucca.bankcli.util.CpfValidator;

/** 1 Client -> N Accounts, which implies separation of objects
 * */

public class Client {

    private final String firstName;
    private final String lastName;
    private final String fullName;
    private final String cpf;
    private final String email;
    private final UUID clientId;

    public Client(String firstName, String lastName, String cpf, String email) {

        if (firstName == null || firstName.isBlank() || lastName == null || lastName.isBlank()) {
            throw new InvalidDataException("First and last name required");
        }
        if (cpf == null || cpf.isBlank() || !CpfValidator.isValid(cpf)) throw new InvalidCpfException("Invalid CPF");

        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = firstName + " " + lastName;
        this.cpf = cpf;
        this.email = email;
        this.clientId = UUID.randomUUID();
    }

    public String getFullName() {
        return fullName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getClientId() {
        return clientId.toString();
    }
}
