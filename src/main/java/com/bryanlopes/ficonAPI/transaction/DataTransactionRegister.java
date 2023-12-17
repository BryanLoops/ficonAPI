package com.bryanlopes.ficonAPI.transaction;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataTransactionRegister(
        @NotBlank
        String name,
        String description,
        @NotNull
        Double value,
        @NotNull
        TransactionType type
) { }