package com.lucca.bankcli.cli;

import com.lucca.bankcli.exception.BankCliException;
import com.lucca.bankcli.model.Account;
import com.lucca.bankcli.model.Client;
import com.lucca.bankcli.service.AccountService;
import com.lucca.bankcli.service.ClientService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private final Scanner scanner;
    private final ClientService clientService;
    private final AccountService accountService;
    private boolean running = true;

    public Menu(ClientService clientService, AccountService accountService) {
        this.clientService = clientService;
        this.accountService = accountService;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        while (running) {
            printMenu();
            int choice = readIntChoice("Escolha uma opção: ");

            switch (choice) {
                case 1 -> handleCreateClient();
                case 2 -> handleSearchClient();
                case 3 -> handleListClients();
                case 4 -> handleUpdateClient();
                case 5 -> handleDeleteClient();
                case 6 -> handleCreateAccount();
                case 7 -> handleSearchAccount();
                case 8 -> handleListAccounts();
                case 9 -> handleDeleteAccount();
                case 10 -> handleDeposit();
                case 11 -> handleWithdraw();
                case 12 -> handleLogin();
                case 13 -> handleTransfer();
                case 14 -> handleQuit();
                default -> System.out.println("Opção inválida, tente novamente.");
            }
        }
    }

    private void printMenu() {
        System.out.println();
        System.out.println("===== BankCLI =====");
        System.out.println("--- Clientes ---");
        System.out.println("1. Criar cliente");
        System.out.println("2. Buscar cliente (por CPF)");
        System.out.println("3. Listar clientes");
        System.out.println("4. Atualizar cliente");
        System.out.println("5. Excluir cliente");
        System.out.println("--- Contas ---");
        System.out.println("6. Criar conta");
        System.out.println("7. Buscar conta (por ID)");
        System.out.println("8. Listar contas");
        System.out.println("9. Excluir conta");
        System.out.println("--- Movimentação ---");
        System.out.println("10. Depositar");
        System.out.println("11. Sacar");
        System.out.println("--- Outros ---");
        System.out.println("12. Login (em breve)");
        System.out.println("13. Transferência entre contas (em breve)");
        System.out.println("14. Sair");
    }

    // ---- Cliente ----

    private void handleCreateClient() {
        try {
            String firstName = readLine("Nome: ");
            String lastName = readLine("Sobrenome: ");
            String cpf = readLine("CPF: ");
            String email = readLine("Email: ");

            Client client = clientService.createClient(firstName, lastName, cpf, email);
            System.out.println("Cliente criado: " + client);
        } catch (BankCliException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void handleSearchClient() {
        try {
            String cpf = readLine("CPF do cliente: ");
            Client client = clientService.searchClient(cpf);
            System.out.println(client);
        } catch (BankCliException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void handleListClients() {
        List<Client> clients = clientService.listAllClients();
        if (clients.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }
        for (Client client : clients) {
            System.out.println(client);
        }
    }

    private void handleUpdateClient() {
        try {
            String cpf = readLine("CPF do cliente a atualizar: ");
            Client client = clientService.searchClient(cpf);

            String firstName = readLine("Novo nome: ");
            String lastName = readLine("Novo sobrenome: ");
            String email = readLine("Novo email: ");

            Client updated = clientService.updateClient(client.getClientId(), firstName, lastName, email);
            System.out.println("Cliente atualizado: " + updated);
        } catch (BankCliException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void handleDeleteClient() {
        try {
            String cpf = readLine("CPF do cliente a excluir: ");
            Client client = clientService.searchClient(cpf);

            clientService.deleteClient(client.getClientId());
            System.out.println("Cliente excluído com sucesso.");
        } catch (BankCliException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    // ---- Conta ----

    private void handleCreateAccount() {
        try {
            String cpf = readLine("CPF do titular: ");
            BigDecimal initialBalance = readBigDecimal("Saldo inicial: ");

            Account account = accountService.createAccount(cpf, initialBalance);
            System.out.println("Conta criada: " + account);
        } catch (BankCliException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void handleSearchAccount() {
        try {
            String id = readLine("ID da conta: ");
            Account account = accountService.searchAccount(id);
            System.out.println(account);
        } catch (BankCliException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void handleListAccounts() {
        List<Account> accounts = accountService.listAllAccounts();
        if (accounts.isEmpty()) {
            System.out.println("Nenhuma conta cadastrada.");
            return;
        }
        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    private void handleDeleteAccount() {
        try {
            String id = readLine("ID da conta a excluir: ");
            accountService.deleteAccount(id);
            System.out.println("Conta excluída com sucesso.");
        } catch (BankCliException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    // ---- Movimentação ----

    private void handleDeposit() {
        try {
            String id = readLine("ID da conta: ");
            BigDecimal amount = readBigDecimal("Valor do depósito: ");

            Account account = accountService.deposit(id, amount);
            System.out.println("Depósito realizado. Novo saldo: " + account.getBalance());
        } catch (BankCliException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void handleWithdraw() {
        try {
            String id = readLine("ID da conta: ");
            BigDecimal amount = readBigDecimal("Valor do saque: ");

            Account account = accountService.withdraw(id, amount);
            System.out.println("Saque realizado. Novo saldo: " + account.getBalance());
        } catch (BankCliException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    // ---- Placeholders ----

    private void handleLogin() {
        System.out.println("Login: funcionalidade ainda não implementada.");
    }

    private void handleTransfer() {
        System.out.println("Transferência entre contas: funcionalidade ainda não implementada.");
    }

    private void handleQuit() {
        running = false;
        System.out.println("Encerrando o BankCLI. Até logo!");
    }

    // ---- Helpers ----

    private String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private int readIntChoice(String prompt) {
        while (true) {
            String input = readLine(prompt);
            try {
                return Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida, digite um número.");
            }
        }
    }

    private BigDecimal readBigDecimal(String prompt) {
        while (true) {
            String input = readLine(prompt);
            try {
                return new BigDecimal(input.trim());
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido, digite um número (ex: 100.50).");
            }
        }
    }
}
