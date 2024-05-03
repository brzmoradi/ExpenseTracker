package com.behrooz.expensetracker.conveter;

import com.behrooz.expensetracker.dto.ExpenseTransactionResponse;
import com.behrooz.expensetracker.entity.ExpenseTransaction;
import com.behrooz.expensetracker.util.DateUtil;
import org.springframework.util.CollectionUtils;

public class ExpenseTransactionConverter
    implements Converter<ExpenseTransaction, ExpenseTransactionResponse> {

  private final TagConverter tagConverter = new TagConverter();
  private final CategoryConverter categoryConverter = new CategoryConverter();
  private final PaymentMethodConverter paymentMethodConverter = new PaymentMethodConverter();

  @Override
  public ExpenseTransactionResponse convert(ExpenseTransaction entity) {
    var response = new ExpenseTransactionResponse();
    response.setId(entity.getId());
    response.setCreatedDate(DateUtil.format(entity.getCreatedDate()));
    response.setDate(DateUtil.format(entity.getDate()));
    response.setAmount(entity.getAmount());
    response.setDescription(entity.getDescription());
    response.setCategory(categoryConverter.convert(entity.getCategory()));
    if (!CollectionUtils.isEmpty(entity.getTags())) {
      response.setTags(tagConverter.convert(entity.getTags()));
    }
    if (entity.getPaymentMethod() != null) {
      response.setPaymentMethod(paymentMethodConverter.convert(entity.getPaymentMethod()));
    }

    return response;
  }
}
