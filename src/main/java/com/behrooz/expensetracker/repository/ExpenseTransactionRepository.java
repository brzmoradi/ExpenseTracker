package com.behrooz.expensetracker.repository;

import com.behrooz.expensetracker.entity.ExpenseTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseTransactionRepository
    extends JpaRepository<ExpenseTransaction, Long>, GetWithUserSecurity<ExpenseTransaction> {}
