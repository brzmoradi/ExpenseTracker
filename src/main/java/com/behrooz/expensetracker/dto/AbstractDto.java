package com.behrooz.expensetracker.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class AbstractDto<ID> {
   private ID id;
}
