package com.behrooz.expensetracker.conveter;

import com.behrooz.expensetracker.dto.BudgetResponse;
import com.behrooz.expensetracker.entity.Budget;
import com.behrooz.expensetracker.util.DateUtil;

public class BudgetConverter implements Converter<Budget, BudgetResponse> {
  private final CategoryConverter categoryConverter = new CategoryConverter();

  @Override
  public BudgetResponse convert(Budget entity) {
    BudgetResponse budgetResponse = new BudgetResponse();
    budgetResponse.setId(entity.getId());
    budgetResponse.setCreatedDate(DateUtil.format(entity.getCreatedDate()));
    budgetResponse.setAmount(entity.getAmount());
    budgetResponse.setThresholdAmount(entity.getThresholdAmount());
    budgetResponse.setEndDate(entity.getEndDate());
    budgetResponse.setStartDate(entity.getStartDate());
    if (entity.getCategory() != null) {
      budgetResponse.setCategory(categoryConverter.convert(entity.getCategory()));
    }
    return budgetResponse;
  }
}
