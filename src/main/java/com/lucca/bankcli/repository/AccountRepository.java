package com.lucca.bankcli.repository;

import com.lucca.bankcli.model.Account;
import java.util.List;
import java.util.Optional;

/** Persistence contract for {@link Account} entities. */
public interface AccountRepository {

    /** Stores a new account. */
    void insertAccount(Account account);

    /** Looks up an account by its ID; empty if none exists. */
    Optional<Account> getAccount(String id);

    /** Removes the account with the given ID, if present. */
    void deleteAccount(String id);

    /** Persists changes made to an already-stored account. */
    void updateAccount(Account account);

    /** Returns every stored account. */
    List<Account> getAllAccounts();

    /** Returns every account owned by the given client. */
    List<Account> getAccountsByClientId(String clientId);
}