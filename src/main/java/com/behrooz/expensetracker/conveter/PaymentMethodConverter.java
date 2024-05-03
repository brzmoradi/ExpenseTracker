package com.behrooz.expensetracker.conveter;

import com.behrooz.expensetracker.dto.PaymentMethodResponse;
import com.behrooz.expensetracker.entity.PaymentMethod;
import com.behrooz.expensetracker.util.DateUtil;

public class PaymentMethodConverter implements Converter<PaymentMethod, PaymentMethodResponse> {
  @Override
  public PaymentMethodResponse convert(PaymentMethod entity) {
    PaymentMethodResponse response = new PaymentMethodResponse();
    response.setId(entity.getId());
    response.setName(entity.getName());
    response.setProvider(entity.getProvider());
    response.setDescription(entity.getDescription());
    response.setCreatedDate(DateUtil.format(entity.getCreatedDate()));
    return response;
  }
}
