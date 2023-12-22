package com.bryanlopes.ficonAPI.transaction;

import com.bryanlopes.ficonAPI.user.User;

public record DataTransactionList(
        Long id,
        String name,
        String description,
        Double value,
        TransactionType type,
        String userName
) {
    public DataTransactionList(Transaction transaction) {
        this(
                transaction.getId(),
                transaction.getName(),
                transaction.getDescription(),
                transaction.getValue(),
                transaction.getType(),
                transaction.getUser().getName());
    }
}
