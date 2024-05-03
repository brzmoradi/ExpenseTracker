package com.behrooz.expensetracker.dto.category;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryRequest {

  @NotBlank(message = "error.validation.notBlank")
  private String name;

  private String description;
}
