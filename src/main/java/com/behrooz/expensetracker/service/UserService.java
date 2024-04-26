package com.behrooz.expensetracker.service;

import com.behrooz.expensetracker.entity.User;

public interface UserService {

  User findById(Integer id);
  User findByUsername(String username);
}
