package com.behrooz.expensetracker.dto.tag;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TagUpdateRequest {
  private String name;

  @Min(value = 0, message = "error.validation.min")
  @Max(value = 10, message = "error.validation.max")
  private Integer degree;
}
