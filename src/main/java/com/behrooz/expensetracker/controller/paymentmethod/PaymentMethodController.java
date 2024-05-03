package com.behrooz.expensetracker.controller.paymentmethod;

import com.behrooz.expensetracker.conveter.PaymentMethodConverter;
import com.behrooz.expensetracker.dto.PageableResponse;
import com.behrooz.expensetracker.dto.PaymentMethodRegisterRequest;
import com.behrooz.expensetracker.dto.PaymentMethodResponse;
import com.behrooz.expensetracker.dto.PaymentMethodUpdateRequest;
import com.behrooz.expensetracker.entity.PaymentMethod;
import com.behrooz.expensetracker.service.PaymentMethodService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PaymentMethodController {

  private final PaymentMethodService service;
  private final PaymentMethodConverter converter = new PaymentMethodConverter();

  @GetMapping("${apis.secure}/payment-methods/{id}")
  public PaymentMethodResponse get(@PathVariable Long id) {
    PaymentMethod paymentMethod = service.get(id);
    return converter.convert(paymentMethod);
  }

  @PostMapping("${apis.secure}/payment-methods")
  public PaymentMethodResponse save(@RequestBody @Valid PaymentMethodRegisterRequest request) {
    PaymentMethod paymentMethod = service.save(request);
    return converter.convert(paymentMethod);
  }

  @PutMapping("${apis.secure}/payment-methods/{id}")
  public PaymentMethodResponse update(
      @PathVariable Long id, @RequestBody @Valid PaymentMethodUpdateRequest request) {
    PaymentMethod paymentMethod = service.update(id, request);
    return converter.convert(paymentMethod);
  }

  @GetMapping("${apis.secure}/payment-methods")
  public PageableResponse<PaymentMethodResponse> getAll(
      @RequestParam(defaultValue = "0") @Min(value = 0, message = "error.validation.min")
          Integer pageIndex,
      @RequestParam(defaultValue = "10")
          @Min(value = 1, message = "error.validation.min")
          @Max(value = 100, message = "error.validation.max")
          Integer pageSize,
      @RequestParam(defaultValue = "createdDate", required = false) String sortBy) {
    Pageable paging = PageRequest.of(pageIndex, pageSize, Sort.by(sortBy).descending());
    Page<PaymentMethod> paymentMethods = service.get(paging);
    return converter.convert(paymentMethods);
  }
}
