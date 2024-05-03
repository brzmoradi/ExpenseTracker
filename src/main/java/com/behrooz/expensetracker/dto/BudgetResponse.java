package com.behrooz.expensetracker.dto;

import com.behrooz.expensetracker.dto.category.CategoryResponse;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BudgetResponse extends AbstractResponseDto<Long> {
  private Long amount;
  private Long thresholdAmount;
  private LocalDate startDate;
  private LocalDate endDate;
  private CategoryResponse category;
}
