package com.bryanlopes.ficonAPI.transaction;

import com.bryanlopes.ficonAPI.user.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Date;

public record DataTransactionRegister(
        @NotBlank
        String name,
        String description,
        @NotNull
        Double value,

        LocalDateTime settlingDate,
        @NotNull
        TransactionType type,
        @NotNull
        User user
) { }