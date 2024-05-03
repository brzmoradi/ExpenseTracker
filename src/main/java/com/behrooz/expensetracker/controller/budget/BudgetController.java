package com.behrooz.expensetracker.controller.budget;

import com.behrooz.expensetracker.conveter.BudgetConverter;
import com.behrooz.expensetracker.dto.BudgetRegisterRequest;
import com.behrooz.expensetracker.dto.BudgetResponse;
import com.behrooz.expensetracker.dto.BudgetUpdateRequest;
import com.behrooz.expensetracker.dto.PageableResponse;
import com.behrooz.expensetracker.service.BudgetService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
public class BudgetController {
  private final BudgetService budgetService;
  private final BudgetConverter converter = new BudgetConverter();

  @GetMapping("${apis.secure}/budgets/{id}")
  public BudgetResponse get(@PathVariable Long id) {
    return converter.convert(budgetService.get(id));
  }

  @PostMapping("${apis.secure}/budgets")
  public BudgetResponse save(@RequestBody @Valid BudgetRegisterRequest request) {
    return converter.convert(budgetService.save(request));
  }

  @PutMapping("${apis.secure}/budgets/{id}")
  public BudgetResponse update(@PathVariable Long id, @RequestBody @Valid BudgetUpdateRequest request) {
    return converter.convert(budgetService.update(id, request));
  }

  @GetMapping("${apis.secure}/budgets")
  public PageableResponse<BudgetResponse> getAll(
      @RequestParam(defaultValue = "0") @Min(value = 0, message = "error.validation.min")
          Integer pageIndex,
      @RequestParam(defaultValue = "10")
          @Min(value = 1, message = "error.validation.min")
          @Max(value = 100, message = "error.validation.max")
          Integer pageSize,
      @RequestParam(defaultValue = "startDate", required = false) String sortBy) {
    Pageable paging = PageRequest.of(pageIndex, pageSize, Sort.by(sortBy).descending());
    return converter.convert(budgetService.get(paging));
  }

}
