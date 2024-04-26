package com.behrooz.expensetracker.exception.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class ApplicationException extends RuntimeException {
  private final List<String> params = new ArrayList<>();

  public List<String> getParams() {
    return params;
  }

  public void setParams(String... params) {
    this.params.addAll(Arrays.asList(params));
  }
}
