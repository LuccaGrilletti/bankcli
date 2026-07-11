package com.lucca.bankcli.service;

import com.lucca.bankcli.model.Account;
import com.lucca.bankcli.model.Client;
import com.lucca.bankcli.repository.ClientRepository;
import com.lucca.bankcli.repository.AccountRepository;

import java.math.BigDecimal;
import java.util.List;


import com.lucca.bankcli.exception.*;

/**
 * Orchestrates account use cases on top of {@link AccountRepository} and
 * {@link ClientRepository}: creation, lookup, listing, deletion, deposits
 * and withdrawals. Delegates balance mutation and its invariants to
 * {@link Account} itself.
 */
public class AccountService {

    private final ClientRepository clientRepository;
    private final AccountRepository accountRepository;

    public AccountService(ClientRepository clientRepository, AccountRepository accountRepository) {
        this.clientRepository = clientRepository;
        this.accountRepository = accountRepository;
    }

    /**
     * Opens a new account for the client identified by {@code cpf}.
     *
     * @throws ClientNotFoundException if no client has the given CPF
     * @throws InvalidDataException if {@code initialBalance} is null or negative
     */
    public Account createAccount(String cpf, BigDecimal initialBalance) {
        Client client = clientRepository.getClientByCpf(cpf)
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));

        Account account = new Account(initialBalance, client.getClientId());
        accountRepository.insertAccount(account);
        return account;
    }

    /**
     * Finds an account by its ID.
     *
     * @throws AccountNotFoundException if no account has the given ID
     */
    public Account searchAccount(String id) {
        return accountRepository.getAccount(id)
                .orElseThrow(() -> new AccountNotFoundException("Account not found: " + id));
    }

    /** Returns every registered account. */
    public List<Account> listAllAccounts() {
        return accountRepository.getAllAccounts();
    }

    /**
     * Deletes the account with the given ID. An account can only be deleted
     * once its balance has been brought to zero.
     *
     * @throws AccountNotFoundException if no account has the given ID
     * @throws InvalidBalanceException if the account balance is not zero
     */
    public void deleteAccount(String id) {
        Account account = accountRepository.getAccount(id)
                .orElseThrow(() -> new AccountNotFoundException("Account not found: " + id));

        if (account.getBalance().compareTo(BigDecimal.ZERO) != 0) {
            throw new InvalidBalanceException("Account must have zero balance to be deleted");
        }

        accountRepository.deleteAccount(id);
    }

    /**
     * Deposits {@code amount} into the account with the given ID and persists the new balance.
     *
     * @throws AccountNotFoundException if no account has the given ID
     * @throws InvalidDataException if {@code amount} is null or not positive
     */
    public Account deposit(String id, BigDecimal amount) {
        Account account = accountRepository.getAccount(id)
                .orElseThrow(() -> new AccountNotFoundException("Account not found: " + id));
        account.deposit(amount);
        accountRepository.updateAccount(account);
        return account;
    }

    /**
     * Withdraws {@code amount} from the account with the given ID and persists the new balance.
     *
     * @throws AccountNotFoundException if no account has the given ID
     * @throws InvalidDataException if {@code amount} is null or not positive
     * @throws InsufficientFundsException if {@code amount} is greater than the current balance
     */
    public Account withdraw(String id, BigDecimal amount) {
        Account account = accountRepository.getAccount(id)
                .orElseThrow(() -> new AccountNotFoundException("Account not found: " + id));
        account.withdraw(amount);
        accountRepository.updateAccount(account);
        return account;
    }
}
