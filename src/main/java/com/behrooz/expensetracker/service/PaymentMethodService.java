package com.behrooz.expensetracker.service;

import com.behrooz.expensetracker.dto.PaymentMethodRegisterRequest;
import com.behrooz.expensetracker.dto.PaymentMethodUpdateRequest;
import com.behrooz.expensetracker.entity.PaymentMethod;

public interface PaymentMethodService
    extends GetAndUpdateService<
        PaymentMethod, PaymentMethodRegisterRequest, PaymentMethodUpdateRequest> {}
