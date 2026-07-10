package com.lucca.bankcli.service;

import com.lucca.bankcli.model.Account;
import com.lucca.bankcli.model.Client;
import com.lucca.bankcli.repository.ClientRepository;
import com.lucca.bankcli.repository.AccountRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


import com.lucca.bankcli.exception.*;

public class AccountService {

    private final ClientRepository clientRepository;
    private final AccountRepository accountRepository;

    public AccountService(ClientRepository clientRepository, AccountRepository accountRepository) {
        this.clientRepository = clientRepository;
        this.accountRepository = accountRepository;
    }

    public Account createAccount(String cpf, BigDecimal initialBalance) {
        Client client = clientRepository.getClientByCpf(cpf)
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));

        Account account = new Account(initialBalance, client.getClientId());
        accountRepository.insertAccount(account);
        return account;
    }

    public Account searchAccount(String id) {
        return accountRepository.getAccount(id)
                .orElseThrow(() -> new AccountNotFoundException("Account not found: " + id));
    }

    public List<Account> getAllAccounts() {
        return accountRepository.getAllAccounts();
    }

    public void deleteAccount(String id) {
        Account account = accountRepository.getAccount(id)
                .orElseThrow(() -> new AccountNotFoundException("Account not found: " + id));

        if (account.getBalance().compareTo(BigDecimal.ZERO) != 0) {
            throw new InvalidBalanceException("Account must have zero balance to be deleted");
        }

        accountRepository.deleteAccount(id);
    }
}
