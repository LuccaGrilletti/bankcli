package com.lucca.bankcli.repository;

import com.lucca.bankcli.model.Client;

import java.util.*;
import java.util.Optional;

public class InMemoryClientRepository implements ClientRepository {

    private final Map<String, Client> clients = new HashMap<>();

    /** Insert a client */
    @Override
    public void insertClient(Client client) {
        clients.put(client.getClientId(), client);
    }

    /**
     * Get a client by ID
     */
    @Override
    public Optional<Client> getClient(String id) {
        return Optional.ofNullable(clients.get(id));
    }

    /** Get all clients */
    @Override
    public List<Client> getAllClients() {
        return new ArrayList<>(clients.values());
    }

    /** Update a client */
    @Override
    public void updateClient(Client client) {
        clients.put(client.getClientId(), client);
    }

    /** Delete a client */
    @Override
    public void deleteClient(String id) {
        clients.remove(id);
    }

    /** Search a client by CPF */
    @Override
    public Optional<Client> getClientByCpf(String cpf) {
        return clients.values().stream()
                .filter(c -> c.getCpf().equals(cpf))
                .findFirst();
    }

}
