package com.lucca.bankcli.repository;

import com.lucca.bankcli.model.Account;
import java.util.*;

/** In-memory {@link AccountRepository} backed by a {@link HashMap}. Not thread-safe. */
public class InMemoryAccountRepository implements AccountRepository {

    private final Map<String, Account> accounts = new HashMap<>();

    /** Inserts a new account, keyed by its account ID. */
    @Override
    public void insertAccount(Account acc) {
        accounts.put(acc.getAccountId(), acc);
    }

    /** Returns the account with the given ID, if present. */
    @Override
    public Optional<Account> getAccount(String id) {
        return Optional.ofNullable(accounts.get(id));
    }

    /** Removes the account with the given ID, if present. */
    @Override
    public void deleteAccount(String id) {
        accounts.remove(id);
    }

    /** Replaces the stored account with the given account (same ID). */
    @Override
    public void updateAccount(Account acc) {
        accounts.put(acc.getAccountId(), acc);
    }

    /** Returns a snapshot list of every stored account. */
    @Override
    public List<Account> getAllAccounts() {
        return new ArrayList<>(accounts.values());
    }

    /** Returns every account whose owner matches the given client ID. */
    @Override
    public List<Account> getAccountsByClientId(String clientId) {
        return accounts.values().stream()
                .filter(acc -> acc.getClientId().equals(clientId))
                .toList();
    }

}
