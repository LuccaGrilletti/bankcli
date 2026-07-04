package com.lucca.bankcli.model;

import java.util.UUID;
import com.lucca.bankcli.util.CpfValidator;

/** 1 Client -> N Accounts, wich implies separation of objects
 * */

public class Client {

    private final String name;
    private final String cpf;
    private final String email;
    private final UUID clientId;

    public Client(String name, String cpf, String email) {

        if (name == null || name.isBlank()) throw new IllegalArgumentException("Invalid name");

        if (cpf == null || cpf.isBlank() || !CpfValidator.isValid(cpf)) throw new IllegalArgumentException("Invalid CPF");

        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.clientId = UUID.randomUUID();
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public UUID getClientId() {
        return clientId;
    }
}
