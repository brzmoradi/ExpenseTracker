package com.behrooz.expensetracker.service.impl;

import com.behrooz.expensetracker.config.security.OnlineUser;
import com.behrooz.expensetracker.dto.ExpenseTransactionRegisterRequest;
import com.behrooz.expensetracker.dto.ExpenseTransactionUpdateRequest;
import com.behrooz.expensetracker.entity.Budget;
import com.behrooz.expensetracker.entity.Category;
import com.behrooz.expensetracker.entity.ExpenseTransaction;
import com.behrooz.expensetracker.entity.PaymentMethod;
import com.behrooz.expensetracker.entity.Tag;
import com.behrooz.expensetracker.exception.BudgetAmountNotAvailableException;
import com.behrooz.expensetracker.exception.ExpenseTransactionNotFoundException;
import com.behrooz.expensetracker.exception.InvalidParameterException;
import com.behrooz.expensetracker.repository.ExpenseTransactionRepository;
import com.behrooz.expensetracker.service.BudgetService;
import com.behrooz.expensetracker.service.CategoryService;
import com.behrooz.expensetracker.service.ExpenseTransactionService;
import com.behrooz.expensetracker.service.PaymentMethodService;
import com.behrooz.expensetracker.service.TagService;
import com.behrooz.expensetracker.util.DateUtil;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
@RequiredArgsConstructor
public class ExpenseTransactionServiceImpl implements ExpenseTransactionService {

  private final ExpenseTransactionRepository repository;
  private final OnlineUser onlineUser;
  private final PaymentMethodService paymentMethodService;
  private final CategoryService categoryService;
  private final TagService tagService;
  private final BudgetService budgetService;

  @Override
  public ExpenseTransaction get(Long id) {
    return repository
        .findByIdAndUserId(id, onlineUser.getId())
        .orElseThrow(ExpenseTransactionNotFoundException::new);
  }

  @Override
  public ExpenseTransaction update(Long id, ExpenseTransactionUpdateRequest update) {
    return null;
  }

  @Transactional(isolation = Isolation.READ_COMMITTED)
  @Override
  public ExpenseTransaction save(ExpenseTransactionRegisterRequest request) {
    if (request == null
        || !DateUtil.isValidateTime(request.getTime())
        || !DateUtil.isValidateDate(request.getDate())
        || request.getAmount() == null
        || request.getCategoryId() == null) throw new InvalidParameterException();
    LocalDate date = DateUtil.getLocalDate(request.getDate());
    LocalTime time = DateUtil.getTime(request.getTime());
    LocalDateTime expenseTime = LocalDateTime.of(date, time);
    Category category = categoryService.get(request.getCategoryId());

    Budget specificBudget = budgetService.findSpecificBudget(category, date);

    if (specificBudget.getAmount() + specificBudget.getThresholdAmount() > request.getAmount()) {
      specificBudget.setAmount(specificBudget.getAmount() - request.getAmount());
    } else {
      throw new BudgetAmountNotAvailableException();
    }

    List<Tag> tags = null;
    if (!CollectionUtils.isEmpty(request.getTagIds())) {
      tags = tagService.get(request.getTagIds());
    }
    PaymentMethod paymentMethod = null;
    if (request.getPaymentMethodId() != null) {
      paymentMethod = paymentMethodService.get(request.getPaymentMethodId());
    }
    ExpenseTransaction expenseTransaction = new ExpenseTransaction();
    expenseTransaction.setAmount(request.getAmount());
    expenseTransaction.setDate(expenseTime);
    expenseTransaction.setDescription(request.getDescription());
    expenseTransaction.setCategory(category);
    expenseTransaction.setTags(tags);
    expenseTransaction.setPaymentMethod(paymentMethod);
    return repository.save(expenseTransaction);
  }

  @Override
  public Page<ExpenseTransaction> get(Pageable pageable) {
    return repository.findAllByUserId(onlineUser.getId(), pageable);
  }

  @Transactional(isolation = Isolation.READ_COMMITTED)
  @Override
  public void delete(Long id) {
    ExpenseTransaction expenseTransaction = this.get(id);
    Budget specificBudget =
        budgetService.findSpecificBudget(
            expenseTransaction.getCategory(), expenseTransaction.getDate().toLocalDate());
    specificBudget.setAmount(specificBudget.getAmount() + expenseTransaction.getAmount());
    repository.delete(expenseTransaction);
  }
}
