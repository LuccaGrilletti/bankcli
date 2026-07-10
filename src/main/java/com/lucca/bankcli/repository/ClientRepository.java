package com.lucca.bankcli.repository;

import com.lucca.bankcli.model.Client;
import java.util.List;
import java.util.Optional;

public interface ClientRepository {
    void insertClient(Client client);
    Optional<Client> getClient(String id);
    List<Client> getAllClients();
    void updateClient(Client client);
    void deleteClient(String id);
    Optional<Client> getClientByCpf(String cpf);
}
