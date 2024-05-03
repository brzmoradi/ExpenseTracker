package com.behrooz.expensetracker.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class AbstractResponseDto<ID> extends AbstractDto<ID> {
  private String createdDate;
}
