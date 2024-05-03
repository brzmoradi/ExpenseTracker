package com.behrooz.expensetracker.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Tag extends Auditable<Long, Integer> {
  private String name;
  private Integer degree;
}
