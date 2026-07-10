package com.lucca.bankcli.repository;

import com.lucca.bankcli.model.Account;
import java.util.List;
import java.util.Optional;

public interface AccountRepository {
    void insertAccount(Account account);
    Optional<Account> getAccount(String id);
    void deleteAccount(String id);
    void updateAccount(Account account);
    List<Account> getAllAccounts();
    List<Account> getAccountsByClientId(String clientId);
}