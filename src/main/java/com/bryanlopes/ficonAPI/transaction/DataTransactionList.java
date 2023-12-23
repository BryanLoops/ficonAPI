package com.bryanlopes.ficonAPI.transaction;

import com.bryanlopes.ficonAPI.user.User;

import java.time.LocalDateTime;

public record DataTransactionList(
        Long id,
        String name,
        String description,
        Double value,
        String settlingDate,
        TransactionType type,
        String userName
) {
    public DataTransactionList(Transaction transaction) {
        this(
                transaction.getId(),
                transaction.getName(),
                transaction.getDescription(),
                transaction.getValue(),
                transaction.getSettlingDate(),
                transaction.getType(),
                transaction.getUser().getName());
    }
}
