package com.behrooz.expensetracker.conveter;

import com.behrooz.expensetracker.dto.category.CategoryResponse;
import com.behrooz.expensetracker.entity.Category;
import com.behrooz.expensetracker.util.DateUtil;

public class CategoryConverter implements Converter<Category, CategoryResponse> {
  @Override
  public CategoryResponse convert(Category entity) {
    CategoryResponse response = new CategoryResponse();
    response.setId(entity.getId());
    response.setName(entity.getName());
    response.setDescription(entity.getDescription());
    response.setCreatedDate(DateUtil.format(entity.getCreatedDate()));
    return response;
  }
}
