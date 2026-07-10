package com.lucca.bankcli.repository;

import com.lucca.bankcli.model.Account;
import java.util.*;

public class InMemoryAccountRepository implements AccountRepository {

    private final Map<String, Account> accounts = new HashMap<>();

    /** Insert accounts*/
    @Override
    public void insertAccount(Account acc) {
        accounts.put(acc.getAccountId(), acc);
    }

    /** Get accounts with ID as String */
    @Override
    public Account getAccount(String id) {
        return accounts.get(id);
    }

    /** Delete accounts with ID as String*/
    @Override
    public void deleteAccount(String id) {
        accounts.remove(id);
    }

    /** Update accounts with ID as String*/
    @Override
    public void updateAccount(Account acc) {
        accounts.put(acc.getAccountId(), acc);
    }

    /** List all accounts */
    @Override
    public List<Account> getAllAccounts() {
        return new ArrayList<>(accounts.values());
    }

}
