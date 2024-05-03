package com.behrooz.expensetracker.dto.category;

import com.behrooz.expensetracker.dto.AbstractResponseDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategoryResponse extends AbstractResponseDto<Long> {
  private String name;
  private String description;
}
