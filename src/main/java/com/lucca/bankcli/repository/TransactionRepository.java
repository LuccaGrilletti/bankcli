package com.lucca.bankcli.repository;

import com.lucca.bankcli.model.Transaction;
import java.util.List;
import java.util.Optional;

/** Persistence contract for {@link Transaction}. */
public interface TransactionRepository {

    /** Stores a new transaction. */
    void insertTransaction(Transaction transaction);

    /** Looks up a transaction by transactionId; empty if none exists*/
    Optional<Transaction> getTransaction(String transactionId);

    /** Returns every stored transaction */
    List<Transaction> getAllTransactions();

    /** Deletes a transaction by its */
    void deleteTransaction(String transactionId);


}
