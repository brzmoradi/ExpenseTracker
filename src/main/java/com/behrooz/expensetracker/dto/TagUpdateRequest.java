package com.behrooz.expensetracker.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TagUpdateRequest extends AbstractDto<Long> {

  private String name;
  private Integer degree;
}
