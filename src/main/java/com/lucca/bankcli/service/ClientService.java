package com.lucca.bankcli.service;

import com.lucca.bankcli.exception.*;
import com.lucca.bankcli.model.Account;
import com.lucca.bankcli.repository.*;
import com.lucca.bankcli.model.Client;

import java.math.BigDecimal;
import java.util.List;


/**
 * Orchestrates client use cases on top of {@link ClientRepository} and
 * {@link AccountRepository}: creation, lookup, listing, update and deletion.
 * Enforces cross-entity rules that don't belong on {@link Client} itself,
 * such as CPF uniqueness and the "no deletion while accounts hold a balance"
 * rule.
 */
public class ClientService {

    private final ClientRepository clientRepository;
    private final AccountRepository accountRepository;

    public ClientService(ClientRepository clientRepository, AccountRepository accountRepository) {
        this.clientRepository = clientRepository;
        this.accountRepository = accountRepository;
    }

    /**
     * Registers a new client.
     *
     * @throws DuplicateCpfException if a client with the given CPF already exists
     * @throws InvalidDataException if the first or last name is null/blank
     * @throws InvalidCpfException if the CPF is null/blank or fails validation
     */
    public Client createClient(String firstName, String lastName, String cpf, String email) {
        if (clientRepository.getClientByCpf(cpf).isPresent()) {
            throw new DuplicateCpfException("CPF already exists: " + cpf);
        }

        Client client = new Client(firstName, lastName, cpf, email);
        clientRepository.insertClient(client);
        return client;
    }

    /**
     * Finds a client by CPF.
     *
     * @throws ClientNotFoundException if no client has the given CPF
     */
    public Client searchClient(String cpf) {
        return clientRepository.getClientByCpf(cpf)
                .orElseThrow(() -> new ClientNotFoundException("Client not found: " + cpf));
    }

    /** Returns every registered client. */
    public List<Client> listAllClients() {
        return clientRepository.getAllClients();
    }

    /**
     * Deletes the client with the given ID along with all of their accounts.
     * Every account is checked for a zero balance first, and only if all of
     * them pass is any account (or the client) actually deleted — this avoids
     * leaving the client in a partially-deleted state.
     *
     * @throws ClientNotFoundException if no client has the given ID
     * @throws InvalidBalanceException if any of the client's accounts has a non-zero balance
     */
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

    /**
     * Updates the first name, last name and email of the client with the given ID.
     *
     * @throws ClientNotFoundException if no client has the given ID
     * @throws InvalidDataException if the first or last name is null/blank
     */
    public Client updateClient(String id, String firstName, String lastName, String email) {
        Client client = clientRepository.getClient(id)
                .orElseThrow(() -> new ClientNotFoundException("Client not found: " + id));

        client.updateProfile(firstName, lastName, email);
        clientRepository.updateClient(client);
        return client;
    }
}
