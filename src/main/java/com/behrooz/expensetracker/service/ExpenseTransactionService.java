package com.behrooz.expensetracker.service;

import com.behrooz.expensetracker.dto.ExpenseTransactionRegisterRequest;
import com.behrooz.expensetracker.dto.ExpenseTransactionUpdateRequest;
import com.behrooz.expensetracker.entity.ExpenseTransaction;

public interface ExpenseTransactionService
    extends GetAndUpdateService<
        ExpenseTransaction, ExpenseTransactionRegisterRequest, ExpenseTransactionUpdateRequest> {

  void delete(Long id);
}
