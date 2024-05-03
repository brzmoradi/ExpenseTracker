package com.behrooz.expensetracker.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PageableResponse<T> {
  private List<T> data;
  private long totalItems;
  private int totalPages;
}
