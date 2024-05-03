package com.behrooz.expensetracker.dto.tag;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class TagRegisterRequest {
  @NotBlank(message = "error.validation.notBlank")
  private String name;

  @NotNull(message = "error.validation.notNull")
  @Min(value = 0, message = "error.validation.min")
  @Max(value = 10, message = "error.validation.max")
  private Integer degree;
}
