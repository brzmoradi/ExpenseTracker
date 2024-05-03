package com.behrooz.expensetracker.repository;

import com.behrooz.expensetracker.entity.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodRepository
    extends JpaRepository<PaymentMethod, Long>, GetWithUserSecurity<PaymentMethod> {}
