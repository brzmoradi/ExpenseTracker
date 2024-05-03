package com.behrooz.expensetracker.controller.category;

import com.behrooz.expensetracker.conveter.CategoryConverter;
import com.behrooz.expensetracker.dto.PageableResponse;
import com.behrooz.expensetracker.dto.category.CategoryRequest;
import com.behrooz.expensetracker.dto.category.CategoryResponse;
import com.behrooz.expensetracker.entity.Category;
import com.behrooz.expensetracker.service.CategoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
public class CategoryController {
  private final CategoryService service;
  private final CategoryConverter converter = new CategoryConverter();

  @GetMapping("${apis.secure}/categories/{id}")
  public CategoryResponse get(@PathVariable Long id) {
    Category category = service.get(id);
    return converter.convert(category);
  }

  @PostMapping("${apis.secure}/categories")
  public CategoryResponse get(@RequestBody @Valid CategoryRequest request) {
    Category category = service.save(request);
    return converter.convert(category);
  }

  @PutMapping("${apis.secure}/categories/{id}")
  public CategoryResponse update(
      @PathVariable Long id, @RequestBody @Valid CategoryRequest request) {
    Category category = service.update(id, request);
    return converter.convert(category);
  }

  @GetMapping("${apis.secure}/categories")
  public PageableResponse<CategoryResponse> getAll(
      @RequestParam(defaultValue = "0") @Min(value = 0, message = "error.validation.min")
          Integer pageIndex,
      @RequestParam(defaultValue = "10")
          @Min(value = 1, message = "error.validation.min")
          @Max(value = 100, message = "error.validation.max")
          Integer pageSize,
      @RequestParam(defaultValue = "createdDate", required = false) String sortBy) {
    Pageable paging = PageRequest.of(pageIndex, pageSize, Sort.by(sortBy).descending());
    return converter.convert(service.get(paging));
  }

  @DeleteMapping("${apis.secure}/categories/{id}")
  public void dalete(@PathVariable Long id) {
    service.delete(id);
  }
}
