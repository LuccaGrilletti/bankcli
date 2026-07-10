package com.lucca.bankcli.service;

import com.lucca.bankcli.model.Account;
import com.lucca.bankcli.model.Client;
import com.lucca.bankcli.repository.ClientRepository;
import com.lucca.bankcli.repository.AccountRepository;

import java.math.BigDecimal;

public class AccountService {
    private final ClientRepository clientRepository;
    private final AccountRepository accountRepository;

    public AccountService(ClientRepository clientRepository, AccountRepository accountRepository) {
        this.clientRepository = clientRepository;
        this.accountRepository = accountRepository;
    }

    public Account createAccount(String cpf, BigDecimal initialBalance) {
        Client client = clientRepository.getClientByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        Account account = new Account(initialBalance, client.getClientId());
        accountRepository.insertAccount(account);
        return account;
    }
}
