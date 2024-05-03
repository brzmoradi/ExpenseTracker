package com.behrooz.expensetracker.dto;

import lombok.Data;

@Data
public class PaymentMethodUpdateRequest {
  private String name;

  private String provider;

  private String description;

  private Boolean active;
}
