package com.membership.service;

import com.membership.domain.Transaction;
import com.membership.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService{

    private TransactionRepository transactionRepository;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
    }


    @Override
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction findById(long id) {
        return transactionRepository.getById(id);
    }

    @Override
    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction update(long id, Transaction transaction) {
        transaction.setTransactionId(id);
        return transactionRepository.save(transaction);
    }

    @Override
    public void deleteById(long id) {
        transactionRepository.deleteById(id);
    }
}
