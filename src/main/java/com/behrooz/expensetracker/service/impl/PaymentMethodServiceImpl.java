package com.behrooz.expensetracker.service.impl;

import com.behrooz.expensetracker.config.security.OnlineUser;
import com.behrooz.expensetracker.dto.PaymentMethodRegisterRequest;
import com.behrooz.expensetracker.dto.PaymentMethodUpdateRequest;
import com.behrooz.expensetracker.entity.PaymentMethod;
import com.behrooz.expensetracker.exception.InvalidParameterException;
import com.behrooz.expensetracker.exception.PaymentMethodNotFoundException;
import com.behrooz.expensetracker.repository.PaymentMethodRepository;
import com.behrooz.expensetracker.service.PaymentMethodService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentMethodServiceImpl implements PaymentMethodService {

  private final PaymentMethodRepository repository;
  private final OnlineUser onlineUser;

  @Override
  public PaymentMethod get(Long id) {
    return repository
        .findByIdAndUserId(id, onlineUser.getId())
        .orElseThrow(PaymentMethodNotFoundException::new);
  }

  @Override
  public PaymentMethod update(Long id, PaymentMethodUpdateRequest request) {
    if (request == null
        || (request.getName() == null
            && request.getProvider() == null
            && request.getDescription() == null)) throw new InvalidParameterException();

    PaymentMethod paymentMethod = this.get(id);
    Optional.of(request.getName()).ifPresent(paymentMethod::setName);
    Optional.of(request.getDescription()).ifPresent(paymentMethod::setDescription);
    Optional.of(request.getProvider()).ifPresent(paymentMethod::setProvider);
    return repository.save(paymentMethod);
  }

  @Override
  public PaymentMethod save(PaymentMethodRegisterRequest request) {
    PaymentMethod paymentMethod = new PaymentMethod();
    paymentMethod.setName(request.getName());
    paymentMethod.setDescription(request.getDescription());
    paymentMethod.setProvider(request.getProvider());
    return repository.save(paymentMethod);
  }

  @Override
  public Page<PaymentMethod> get(Pageable pageable) {
    return repository.findAllByUserId(onlineUser.getId(), pageable);
  }
}
