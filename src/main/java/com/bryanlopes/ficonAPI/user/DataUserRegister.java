package com.bryanlopes.ficonAPI.user;

import jakarta.validation.constraints.NotBlank;

public record DataUserRegister(@NotBlank String name) {
}
