package com.behrooz.expensetracker.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PaymentMethodResponse extends AbstractResponseDto<Long> {
  private String name;
  private String description;
  private String provider;
}
