package com.behrooz.expensetracker.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GetAndUpdateService<E, R, U> {

  E get(Long id);

  E update(Long id , U update);

  E save(R registerRequest);

  Page<E> get(Pageable pageable);
}
