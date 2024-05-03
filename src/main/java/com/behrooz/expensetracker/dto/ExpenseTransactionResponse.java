package com.behrooz.expensetracker.dto;

import com.behrooz.expensetracker.dto.category.CategoryResponse;
import com.behrooz.expensetracker.dto.tag.TagResponse;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExpenseTransactionResponse extends AbstractResponseDto<Long> {

  private Long amount;
  private String description;
  private String date;
  private List<TagResponse> tags;
  private CategoryResponse category;
  private PaymentMethodResponse paymentMethod;
}
