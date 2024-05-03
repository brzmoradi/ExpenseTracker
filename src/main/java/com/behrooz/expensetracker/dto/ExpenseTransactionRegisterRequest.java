package com.behrooz.expensetracker.dto;

import com.behrooz.expensetracker.util.DateUtil;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ExpenseTransactionRegisterRequest {
  @NotBlank(message = "error.validation.notBlank")
  @Pattern(regexp = DateUtil.DATE_REGEX_PATTERN, message = "error.validation.regex.date")
  private String date;

  @NotBlank(message = "error.validation.notBlank")
  @Pattern(regexp = DateUtil.TIME_REGEX_PATTERN, message = "error.validation.regex.time")
  private String time;

  @NotNull(message = "error.validation.notNull")
  private Long amount;

  private String description;
  private Long paymentMethodId;

  @NotNull(message = "error.validation.notNull")
  private Long categoryId;

  private List<Long> tagIds;
}
