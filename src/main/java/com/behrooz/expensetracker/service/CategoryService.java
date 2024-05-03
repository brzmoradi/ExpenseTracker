package com.behrooz.expensetracker.service;

import com.behrooz.expensetracker.dto.category.CategoryRequest;
import com.behrooz.expensetracker.entity.Category;

public interface CategoryService
    extends GetAndUpdateService<Category, CategoryRequest, CategoryRequest> {
    void delete(Long id);
}
