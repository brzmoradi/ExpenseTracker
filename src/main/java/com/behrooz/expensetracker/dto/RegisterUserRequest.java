package com.behrooz.expensetracker.dto;

import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class RegisterUserRequest {
  @NotBlank(message = "error.validation.notBlank")
  private String username;
  @NotBlank(message = "error.validation.notBlank")
  private String title;
  @NotEmpty(message = "error.validation.notEmpty")
  private Set<Integer> roleIds;
}
