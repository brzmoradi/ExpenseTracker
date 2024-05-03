package com.behrooz.expensetracker.controller.expense;

import com.behrooz.expensetracker.conveter.ExpenseTransactionConverter;
import com.behrooz.expensetracker.dto.ExpenseTransactionRegisterRequest;
import com.behrooz.expensetracker.dto.ExpenseTransactionResponse;
import com.behrooz.expensetracker.dto.ExpenseTransactionUpdateRequest;
import com.behrooz.expensetracker.service.ExpenseTransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
public class ExpenseTransactionController {
  private final ExpenseTransactionService service;
  private final ExpenseTransactionConverter converter = new ExpenseTransactionConverter();

  @GetMapping("${apis.secure}/expense-transaction/{id}")
  public ExpenseTransactionResponse get(@PathVariable Long id) {
    return converter.convert(service.get(id));
  }

  @PostMapping("${apis.secure}/expense-transaction")
  public ExpenseTransactionResponse save(
      @RequestBody @Valid ExpenseTransactionRegisterRequest request) {
    return converter.convert(service.save(request));
  }

  @PutMapping("${apis.secure}/expense-transaction/{id}")
  public ExpenseTransactionResponse update(
      @PathVariable Long id, @RequestBody @Valid ExpenseTransactionUpdateRequest request) {
    return converter.convert(service.update(id, request));
  }

  @DeleteMapping("${apis.secure}/expense-transaction/{id}")
  public void delete(@PathVariable Long id) {
    service.delete(id);
  }
}
