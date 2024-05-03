package com.behrooz.expensetracker.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PaymentMethodRegisterRequest {
  @NotBlank(message = "error.validation.notBlank")
  private String name;

  @NotBlank(message = "error.validation.notBlank")
  private String provider;

  private String description;
}
