package com.lucca.bankcli.model;

import java.util.UUID;

import com.lucca.bankcli.exception.*;
import com.lucca.bankcli.util.CpfValidator;

/** 1 Client -> N Accounts, which implies separation of objects
 * */

public class Client {

    private String firstName;
    private String lastName;
    private String fullName;
    private final String cpf;
    private String email;
    private final UUID clientId;

    /**
     * Creates a new client.
     *
     * @throws InvalidDataException if the first or last name is null/blank
     * @throws InvalidCpfException if the CPF is null/blank or fails the check-digit validation
     */
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

    /**
     * Updates the client's first name, last name and email in place.
     * CPF is immutable and cannot be changed through this method.
     *
     * @throws InvalidDataException if the first or last name is null/blank
     */
    public void updateProfile(String firstName, String lastName, String email) {
        if (firstName == null || firstName.isBlank() || lastName == null || lastName.isBlank()) {
            throw new InvalidDataException("First and last name required");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = firstName + " " + lastName;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Client{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", cpf='" + cpf + '\'' +
                ", email='" + email + '\'' +
                ", clientId=" + clientId +
                '}';
    }
}
