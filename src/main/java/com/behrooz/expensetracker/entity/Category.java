package com.behrooz.expensetracker.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Category extends Auditable<Long, Integer> {
  private String name;
  private String description;
}
