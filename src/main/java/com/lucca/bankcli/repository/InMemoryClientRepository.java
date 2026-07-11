package com.lucca.bankcli.repository;

import com.lucca.bankcli.model.Client;

import java.util.*;
import java.util.Optional;

/** In-memory {@link ClientRepository} backed by a {@link HashMap}. Not thread-safe. */
public class InMemoryClientRepository implements ClientRepository {

    private final Map<String, Client> clients = new HashMap<>();

    /** Inserts a new client, keyed by its client ID. */
    @Override
    public void insertClient(Client client) {
        clients.put(client.getClientId(), client);
    }

    /** Returns the client with the given ID, if present. */
    @Override
    public Optional<Client> getClient(String id) {
        return Optional.ofNullable(clients.get(id));
    }

    /** Returns a snapshot list of every stored client. */
    @Override
    public List<Client> getAllClients() {
        return new ArrayList<>(clients.values());
    }

    /** Replaces the stored client with the given client (same ID). */
    @Override
    public void updateClient(Client client) {
        clients.put(client.getClientId(), client);
    }

    /** Removes the client with the given ID, if present. */
    @Override
    public void deleteClient(String id) {
        clients.remove(id);
    }

    /** Returns the client whose CPF matches, if present. */
    @Override
    public Optional<Client> getClientByCpf(String cpf) {
        return clients.values().stream()
                .filter(c -> c.getCpf().equals(cpf))
                .findFirst();
    }

}
