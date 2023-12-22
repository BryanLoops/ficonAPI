package com.bryanlopes.ficonAPI.controller;

import com.bryanlopes.ficonAPI.transaction.DataTransactionList;
import com.bryanlopes.ficonAPI.transaction.DataTransactionRegister;
import com.bryanlopes.ficonAPI.transaction.Transaction;
import com.bryanlopes.ficonAPI.transaction.TransactionRepository;
import com.bryanlopes.ficonAPI.user.User;
import com.bryanlopes.ficonAPI.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    @Transactional
    public void register(@RequestBody @Valid DataTransactionRegister data) {
        User user = userRepository.findById(data.user().getId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + data.user().getId()));

        Transaction transaction = new Transaction(data);
        transaction.setUser(user);

        repository.save(transaction);
        transaction.updateWallet();
    }

    @GetMapping
    public Page<DataTransactionList> search(@PageableDefault(size=10, sort={"name"}) Pageable pagination) {
        return repository.findAll(pagination).map(DataTransactionList::new);
    }

    @GetMapping("/id/{transactionId}")
    public DataTransactionList searchById(@PathVariable Long transactionId) {
        Transaction transaction = repository.findByIdAndActiveIsTrue(transactionId);

        return new DataTransactionList(transaction);
    }

    @GetMapping("/name/{transactionName}")
    public Page<DataTransactionList> searchByName(@PathVariable String transactionName, @PageableDefault(size=10, sort={"id"}) Pageable pageable) {
        Page<Transaction> transactions = repository.findByNameIgnoreCaseAndActiveIsTrue(transactionName, pageable);

        List<DataTransactionList> dataTransactionList = transactions
                .stream()
                .map(DataTransactionList::new)
                .collect(Collectors.toList());

        return new PageImpl<>(dataTransactionList, pageable, transactions.getTotalElements());
    }

    @PatchMapping("/{transactionId}/activate")
    public DataTransactionList activateTransaction(@PathVariable Long transactionId) {
        return updateTransactionStatus(transactionId, true);
    }

    @PatchMapping("/{transactionId}/deactivate")
    public DataTransactionList deactivateTransaction(@PathVariable Long transactionId) {
        return updateTransactionStatus(transactionId, false);
    }

    private DataTransactionList updateTransactionStatus(Long transactionId, boolean activate) {
        Transaction existingTransaction = repository.findById(transactionId)
                .orElseThrow(() -> new EntityNotFoundException("Transaction not found with ID: " + transactionId));

        User user = userRepository.findById(existingTransaction.getUser().getId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + existingTransaction.getUser().getId()));

        existingTransaction.setActive(activate);

        Transaction updatedTransaction = repository.save(existingTransaction);
        updatedTransaction.setUser(user);
        updatedTransaction.updateWallet();
        repository.save(updatedTransaction);

        return new DataTransactionList(updatedTransaction);
    }
}
