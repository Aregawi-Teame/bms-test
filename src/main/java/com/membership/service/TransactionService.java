package com.membership.service;

import com.membership.domain.Transaction;

import java.util.List;

public interface TransactionService {

    public List<Transaction> findAll();

    public Transaction findById(long id);

    public Transaction save(Transaction transaction);

    public Transaction update(long id, Transaction transaction);

    public void deleteById(long id);
}
