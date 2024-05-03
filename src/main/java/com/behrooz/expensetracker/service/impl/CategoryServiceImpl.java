package com.behrooz.expensetracker.service.impl;

import com.behrooz.expensetracker.config.security.OnlineUser;
import com.behrooz.expensetracker.dto.category.CategoryRequest;
import com.behrooz.expensetracker.entity.Category;
import com.behrooz.expensetracker.exception.CategoryNotFoundException;
import com.behrooz.expensetracker.exception.InvalidParameterException;
import com.behrooz.expensetracker.repository.CategoryRepository;
import com.behrooz.expensetracker.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository repository;
  private final OnlineUser onlineUser;

  @Override
  public Category get(Long id) {
    return repository
        .findByIdAndUserId(id, onlineUser.getId())
        .orElseThrow(CategoryNotFoundException::new);
  }

  @Transactional
  @Override
  public Category save(CategoryRequest request) {
    if (request == null || Strings.isEmpty(request.getName()))
      throw new InvalidParameterException();
    Category category = new Category();
    category.setName(request.getName());
    category.setDescription(request.getDescription());
    return repository.save(category);
  }

  @Transactional
  @Override
  public Category update(Long id, CategoryRequest request) {
    if (request == null || Strings.isEmpty(request.getName()))
      throw new InvalidParameterException();
    Category category = this.get(id);
    category.setName(request.getName());
    category.setDescription(request.getDescription());
    return repository.save(category);
  }

  @Override
  public Page<Category> get(Pageable pageable) {
    return repository.findAllByUserId(onlineUser.getId(), pageable);
  }

  @Override
  public void delete(Long id) {
    Category category = get(id);
    repository.delete(category);

  }
}
