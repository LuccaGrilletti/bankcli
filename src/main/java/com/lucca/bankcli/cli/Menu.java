package com.lucca.bankcli.cli;

import com.lucca.bankcli.exception.BankCliException;
import com.lucca.bankcli.model.Account;
import com.lucca.bankcli.model.Client;
import com.lucca.bankcli.service.AccountService;
import com.lucca.bankcli.service.ClientService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

/**
 * Text-based presentation layer for the application. Reads user input from
 * the console, dispatches each menu option to a dedicated handler method,
 * and delegates all business logic to {@link ClientService} and
 * {@link AccountService}. Every domain error surfaces as a
 * {@link BankCliException} subtype, which is caught per handler and printed
 * as a plain message instead of a stack trace.
 */
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

    /** Runs the main loop: prints the menu, reads a choice, dispatches it, and repeats until the user quits. */
    public void run() {
        while (running) {
            printMenu();
            int choice = readIntChoice("Choose an option: ");

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
                default -> System.out.println("Invalid option, try again.");
            }
        }
    }

    private void printMenu() {
        System.out.println();
        System.out.println("===== BankCLI =====");
        System.out.println("--- Clients ---");
        System.out.println("1. Create client");
        System.out.println("2. Search client (by CPF)");
        System.out.println("3. List clients");
        System.out.println("4. Update client");
        System.out.println("5. Delete client");
        System.out.println("--- Accounts ---");
        System.out.println("6. Create account");
        System.out.println("7. Search account (by ID)");
        System.out.println("8. List accounts");
        System.out.println("9. Delete account");
        System.out.println("--- Transactions ---");
        System.out.println("10. Deposit");
        System.out.println("11. Withdraw");
        System.out.println("--- Other ---");
        System.out.println("12. Login (coming soon)");
        System.out.println("13. Transfer between accounts (coming soon)");
        System.out.println("14. Quit");
    }

    // ---- Client ----

    private void handleCreateClient() {
        try {
            String firstName = readLine("First name: ");
            String lastName = readLine("Last name: ");
            String cpf = readLine("CPF: ");
            String email = readLine("Email: ");

            Client client = clientService.createClient(firstName, lastName, cpf, email);
            System.out.println("Client created: " + client);
        } catch (BankCliException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void handleSearchClient() {
        try {
            String cpf = readLine("Client CPF: ");
            Client client = clientService.searchClient(cpf);
            System.out.println(client);
        } catch (BankCliException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void handleListClients() {
        List<Client> clients = clientService.listAllClients();
        if (clients.isEmpty()) {
            System.out.println("No clients registered.");
            return;
        }
        for (Client client : clients) {
            System.out.println(client);
        }
    }

    private void handleUpdateClient() {
        try {
            String cpf = readLine("CPF of the client to update: ");
            Client client = clientService.searchClient(cpf);

            String firstName = readLine("New first name: ");
            String lastName = readLine("New last name: ");
            String email = readLine("New email: ");

            Client updated = clientService.updateClient(client.getClientId(), firstName, lastName, email);
            System.out.println("Client updated: " + updated);
        } catch (BankCliException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void handleDeleteClient() {
        try {
            String cpf = readLine("CPF of the client to delete: ");
            Client client = clientService.searchClient(cpf);

            clientService.deleteClient(client.getClientId());
            System.out.println("Client deleted successfully.");
        } catch (BankCliException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ---- Account ----

    private void handleCreateAccount() {
        try {
            String cpf = readLine("Account holder CPF: ");
            BigDecimal initialBalance = readBigDecimal("Initial balance: ");

            Account account = accountService.createAccount(cpf, initialBalance);
            System.out.println("Account created: " + account);
        } catch (BankCliException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void handleSearchAccount() {
        try {
            String id = readLine("Account ID: ");
            Account account = accountService.searchAccount(id);
            System.out.println(account);
        } catch (BankCliException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void handleListAccounts() {
        List<Account> accounts = accountService.listAllAccounts();
        if (accounts.isEmpty()) {
            System.out.println("No accounts registered.");
            return;
        }
        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    private void handleDeleteAccount() {
        try {
            String id = readLine("ID of the account to delete: ");
            accountService.deleteAccount(id);
            System.out.println("Account deleted successfully.");
        } catch (BankCliException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ---- Transactions ----

    private void handleDeposit() {
        try {
            String id = readLine("Account ID: ");
            BigDecimal amount = readBigDecimal("Deposit amount: ");

            Account account = accountService.deposit(id, amount);
            System.out.println("Deposit completed. New balance: " + account.getBalance());
        } catch (BankCliException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void handleWithdraw() {
        try {
            String id = readLine("Account ID: ");
            BigDecimal amount = readBigDecimal("Withdrawal amount: ");

            Account account = accountService.withdraw(id, amount);
            System.out.println("Withdrawal completed. New balance: " + account.getBalance());
        } catch (BankCliException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ---- Placeholders ----

    private void handleLogin() {
        System.out.println("Login: feature not implemented yet.");
    }

    private void handleTransfer() {
        System.out.println("Transfer between accounts: feature not implemented yet.");
    }

    private void handleQuit() {
        running = false;
        System.out.println("Shutting down BankCLI. Goodbye!");
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
                System.out.println("Invalid input, please enter a number.");
            }
        }
    }

    private BigDecimal readBigDecimal(String prompt) {
        while (true) {
            String input = readLine(prompt);
            try {
                return new BigDecimal(input.trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid value, please enter a number (e.g. 100.50).");
            }
        }
    }
}
