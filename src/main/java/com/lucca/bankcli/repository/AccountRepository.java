package com.lucca.bankcli.repository;

import com.lucca.bankcli.model.Account;
import java.util.List;

public interface AccountRepository {
    void insertAccount(Account account);
    Account getAccount(String id);
    void deleteAccount(String id);
    void updateAccount(Account account);
    List<Account> getAllAccounts();
}