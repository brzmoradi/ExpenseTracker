package com.behrooz.expensetracker.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ExpenseTransactionUpdateRequest {
  private String time;
  private String date;
  private Long amount;
  private String description;
  private Long paymentMethodId;
  private List<Long> tagIds;
}
