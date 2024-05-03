package com.behrooz.expensetracker.repository;

import com.behrooz.expensetracker.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository
    extends JpaRepository<Category, Long>, GetWithUserSecurity<Category> {}
