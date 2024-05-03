package com.behrooz.expensetracker.service.impl;

import com.behrooz.expensetracker.config.security.OnlineUser;
import com.behrooz.expensetracker.dto.BudgetRegisterRequest;
import com.behrooz.expensetracker.dto.BudgetUpdateRequest;
import com.behrooz.expensetracker.entity.Budget;
import com.behrooz.expensetracker.entity.Category;
import com.behrooz.expensetracker.exception.BudgetNotFoundException;
import com.behrooz.expensetracker.exception.BudgetSpecificNotFoundException;
import com.behrooz.expensetracker.exception.InvalidParameterException;
import com.behrooz.expensetracker.exception.InvalidDateParameterException;
import com.behrooz.expensetracker.repository.BudgetRepository;
import com.behrooz.expensetracker.service.BudgetService;
import com.behrooz.expensetracker.service.CategoryService;
import com.behrooz.expensetracker.util.DateUtil;
import java.time.LocalDate;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BudgetServiceImpl implements BudgetService {
  private final BudgetRepository repository;
  private final OnlineUser onlineUser;
  private final CategoryService categoryService;

  @Override
  public Budget get(Long id) {
    return repository
        .findByIdAndUserId(id, onlineUser.getId())
        .orElseThrow(BudgetNotFoundException::new);
  }

  @Transactional
  @Override
  public Budget update(Long id, BudgetUpdateRequest update) {
    Budget budget = this.get(id);
    Optional.of(update.getThresholdAmount()).ifPresent(budget::setThresholdAmount);
    Optional.ofNullable(DateUtil.getLocalDate(update.getStartDate()))
        .ifPresent(budget::setStartDate);
    Optional.ofNullable(DateUtil.getLocalDate(update.getEndDate())).ifPresent(budget::setEndDate);
    return repository.save(budget);
  }

  @Override
  public Budget save(BudgetRegisterRequest registerRequest) {
    if (registerRequest == null
        || DateUtil.isValidateDate(registerRequest.getStartDate())
        || DateUtil.isValidateDate(registerRequest.getEndDate())
        || registerRequest.getAmount() == null
        || registerRequest.getThresholdAmount() == null
        || registerRequest.getCategoryId() == null) throw new InvalidParameterException();
    Category category = categoryService.get(registerRequest.getCategoryId());
    Budget budget = new Budget();
    budget.setAmount(registerRequest.getAmount());
    budget.setCategory(category);
    budget.setThresholdAmount(registerRequest.getThresholdAmount());
    budget.setStartDate(getDate(registerRequest.getStartDate()));
    budget.setEndDate(getDate(registerRequest.getEndDate()));
    return repository.save(budget);
  }

  @Override
  public Page<Budget> get(Pageable pageable) {
    return repository.findAllByUserId(onlineUser.getId(), pageable);
  }

  private LocalDate getDate(String inputDate) {
    return Optional.ofNullable(DateUtil.getLocalDate(inputDate))
        .orElseThrow(InvalidDateParameterException::new);
  }

  @Override
  public Budget findSpecificBudget(Category category, LocalDate time) {
    return repository
        .findByUserIdAndCategoryAndStartDateIsBeforeAndEndDateIsAfter(
            onlineUser.getId(), category, time, time)
        .orElseThrow(BudgetSpecificNotFoundException::new);
  }
}
