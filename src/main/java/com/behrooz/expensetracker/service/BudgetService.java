package com.behrooz.expensetracker.service;

import com.behrooz.expensetracker.dto.BudgetRegisterRequest;
import com.behrooz.expensetracker.dto.BudgetUpdateRequest;
import com.behrooz.expensetracker.entity.Budget;
import com.behrooz.expensetracker.entity.Category;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface BudgetService
    extends GetAndUpdateService<Budget, BudgetRegisterRequest, BudgetUpdateRequest> {

   Budget findSpecificBudget(Category category , LocalDate time);
}
