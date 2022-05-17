package com.membership.controller;

import com.membership.domain.Transaction;
import com.membership.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<Transaction> findAll(){
        return transactionService.findAll();
    }

    @GetMapping("/{id}")
    public Transaction findById(@PathVariable(name = "id") long id){
        return transactionService.findById(id);
    }

    @PostMapping
    public Transaction addNewTransaction(@RequestBody Transaction transaction){
        return transactionService.save(transaction);
    }

    @PutMapping("/{id}")
    public Transaction updateTransaction(@PathVariable(name = "id") long id, @RequestBody Transaction transaction){
        return transactionService.update(id, transaction);
    }

    @DeleteMapping("/{id}")
    public String deleteTransaction(@PathVariable(name = "id") long id){
        transactionService.deleteById(id);
        return "Successfully deleted transaction.";
    }
}
