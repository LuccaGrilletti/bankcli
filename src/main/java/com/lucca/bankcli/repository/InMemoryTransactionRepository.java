package com.lucca.bankcli.repository;

import com.lucca.bankcli.model.Transaction;
import java.util.*;

/** In-memory {@link TransactionRepository} backed by a {@link HashMap}. Not thread-safe*/
public class InMemoryTransactionRepository implements TransactionRepository {

    private final Map<String, Transaction> transactions = new HashMap<>();

    /** Insert a new transaction, keyed by its transactionId */
    @Override
    public void insertTransaction(Transaction transaction) {
        transactions.put(transaction.getTransactionId(), transaction);
    }

    /** Get a transaction by its transactionId */
    @Override
    public Optional<Transaction> getTransaction(String transactionId) {
        return Optional.ofNullable(transactions.get(transactionId));
    }

    /** List all transactions */
    @Override
    public List<Transaction> getAllTransactions() {
        return new ArrayList<>(transactions.values());
    }

    /** Delete a transaction by its transactionId */
    @Override
    public void deleteTransaction(String transactionId) {
        transactions.remove(transactionId);
    }

}
