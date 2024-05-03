package com.behrooz.expensetracker.repository;

import com.behrooz.expensetracker.entity.Budget;
import com.behrooz.expensetracker.entity.Category;
import jakarta.persistence.LockModeType;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

public interface BudgetRepository extends JpaRepository<Budget, Long>, GetWithUserSecurity<Budget> {

  @Lock(LockModeType.PESSIMISTIC_WRITE)
  Optional<Budget> findByUserIdAndCategoryAndStartDateIsBeforeAndEndDateIsAfter(
      Integer userId, Category category, LocalDate startDate, LocalDate endDate);
}
