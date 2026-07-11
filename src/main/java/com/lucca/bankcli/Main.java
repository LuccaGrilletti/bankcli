package com.lucca.bankcli;

import com.lucca.bankcli.cli.Menu;
import com.lucca.bankcli.repository.AccountRepository;
import com.lucca.bankcli.repository.ClientRepository;
import com.lucca.bankcli.repository.InMemoryAccountRepository;
import com.lucca.bankcli.repository.InMemoryClientRepository;
import com.lucca.bankcli.service.AccountService;
import com.lucca.bankcli.service.ClientService;

public class Main {
    public static void main(String[] args) {
        ClientRepository clientRepository = new InMemoryClientRepository();
        AccountRepository accountRepository = new InMemoryAccountRepository();

        ClientService clientService = new ClientService(clientRepository, accountRepository);
        AccountService accountService = new AccountService(clientRepository, accountRepository);

        Menu menu = new Menu(clientService, accountService);
        menu.run();
    }
}
