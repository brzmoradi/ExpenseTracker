package com.behrooz.expensetracker.dto;

import com.behrooz.expensetracker.util.DateUtil;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BudgetUpdateRequest {
  private Long thresholdAmount;

  @Pattern(regexp = DateUtil.DATE_REGEX_PATTERN, message = "error.validation.regex.date")
  private String startDate;

  @Pattern(regexp = DateUtil.DATE_REGEX_PATTERN, message = "error.validation.regex.date")
  private String endDate;
}
