package com.bryanlopes.ficonAPI.controller;

import com.bryanlopes.ficonAPI.transaction.DataTransactionRegister;
import com.bryanlopes.ficonAPI.transaction.Transaction;
import com.bryanlopes.ficonAPI.transaction.TransactionRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionRepository repository;

    @PostMapping
    @Transactional
    public void register(@RequestBody @Valid DataTransactionRegister data) {
        repository.save(new Transaction(data));
    }
}
