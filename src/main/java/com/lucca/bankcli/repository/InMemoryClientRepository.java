package com.lucca.bankcli.repository;

import com.lucca.bankcli.model.Client;

import java.util.*;

public class InMemoryClientRepository {

    private final Map<String, Client> clients = new HashMap<>();

    /** Insert a client */
    public void insertClient(Client client) {
        clients.put(client.getClientId(), client);
    }

    /** Get a client by ID */
    public Client getClient(String id) {
        return clients.get(id);
    }

    /** Get all clients */
    public List<Client> getAllClients() {
        return new ArrayList<>(clients.values());
    }

    /** Update a client */
    public void updateClient(Client client) {
        clients.put(client.getClientId(), client);
    }

    /** Delete a client */
    public void deleteClient(String id) {
        clients.remove(id);
    }

    /** Search a client by CPF */
    public Client getClientByCpf(String cpf) {
        return clients.values().stream()                        // NTS: .values -> valores do mapa / .stream -> percorre registros / == for ( Client c : clients.values())
                .filter(c -> c.getCpf().equals(cpf))     //  == if (clients.getCpf().equals(cpf)
                .findFirst()                                   //  caso ache, retorna
                .orElse(null);                           //  caso não, retorna null
    }

}
