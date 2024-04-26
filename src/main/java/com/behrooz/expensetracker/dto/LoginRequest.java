package com.behrooz.expensetracker.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
    @NotBlank(message = "error.validation.notBlank") String user,
    @NotBlank(message = "error.validation.notBlank") String password) {}
