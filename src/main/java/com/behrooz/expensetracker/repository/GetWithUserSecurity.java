package com.behrooz.expensetracker.repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GetWithUserSecurity<T> {
  Optional<T> findByIdAndUserId(Long id, Integer userId);

  Page<T> findAllByUserId(Integer userId, Pageable pageable);
}
