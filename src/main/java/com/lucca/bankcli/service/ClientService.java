package com.lucca.bankcli.service;

import com.lucca.bankcli.exception.*;
import com.lucca.bankcli.model.Account;
import com.lucca.bankcli.repository.*;
import com.lucca.bankcli.model.Client;

import java.math.BigDecimal;
import java.util.List;


public class ClientService {

    private final ClientRepository clientRepository;
    private final AccountRepository accountRepository;

    public ClientService(ClientRepository clientRepository, AccountRepository accountRepository) {
        this.clientRepository = clientRepository;
        this.accountRepository = accountRepository;
    }

    public Client createClient(String firstName, String lastName, String cpf, String email) {
        if (clientRepository.getClientByCpf(cpf).isPresent()) {
            throw new DuplicateCpfException("CPF already exists: " + cpf);
        }

        Client client = new Client(firstName, lastName, cpf, email);
        clientRepository.insertClient(client);
        return client;
    }

    public Client searchClient(String cpf) {
        return clientRepository.getClientByCpf(cpf)
                .orElseThrow(() -> new ClientNotFoundException("Client not found: " + cpf));
    }

    public List<Client> listAllClients() {
        return clientRepository.getAllClients();
    }

    public void deleteClient(String clientId) {
        Client client = clientRepository.getClient(clientId)
                .orElseThrow(() -> new ClientNotFoundException("Client not found: " + clientId));

        List<Account> clientAccounts = accountRepository.getAccountsByClientId(clientId);

        for (Account account : clientAccounts) {
            if (account.getBalance().compareTo(BigDecimal.ZERO) != 0) {
                throw new InvalidBalanceException("Client has accounts with non-zero balance");
            }
        }

        for (Account account : clientAccounts) {
            accountRepository.deleteAccount(account.getAccountId());
        }
        clientRepository.deleteClient(clientId);
    }

    public Client updateClient(String id, String firstName, String lastName, String email) {
        Client client = clientRepository.getClient(id)
                .orElseThrow(() -> new ClientNotFoundException("Client not found: " + id));

        client.updateProfile(firstName, lastName, email);
        clientRepository.updateClient(client);
        return client;
    }
}
