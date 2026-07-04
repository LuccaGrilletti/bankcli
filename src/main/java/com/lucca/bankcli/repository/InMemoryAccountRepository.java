package com.lucca.bankcli.repository;

import com.lucca.bankcli.model.Account;
import java.util.*;

public class InMemoryAccountRepository {

    private Map<String, Account> accounts = new HashMap<>();

    /** Insert accounts*/
    public void insertAccount(Account acc) {
        accounts.put(acc.getId(), acc);
    }

    /** Get accounts with ID as String */
    public Account getAccount(String id) {
        return accounts.get(id);
    }

    /** Remove accounts with ID as String*/
    public void removeAccount(String id) {
        accounts.remove(id);
    }

    /** Update accounts with ID as String*/
    public void updateAccount(Account acc) {
        accounts.put(acc.getId(), acc);
    }

    /** List all accounts */
    public List<Account> getAllAccounts() {
        return new ArrayList<>(accounts.values());
    }

}
