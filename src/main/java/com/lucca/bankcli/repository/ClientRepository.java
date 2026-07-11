package com.lucca.bankcli.repository;

import com.lucca.bankcli.model.Client;
import java.util.List;
import java.util.Optional;

/** Persistence contract for {@link Client} entities. */
public interface ClientRepository {

    /** Stores a new client. */
    void insertClient(Client client);

    /** Looks up a client by its ID; empty if none exists. */
    Optional<Client> getClient(String id);

    /** Returns every stored client. */
    List<Client> getAllClients();

    /** Persists changes made to an already-stored client. */
    void updateClient(Client client);

    /** Removes the client with the given ID, if present. */
    void deleteClient(String id);

    /** Looks up a client by CPF; empty if none exists. */
    Optional<Client> getClientByCpf(String cpf);
}
