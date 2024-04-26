package com.behrooz.expensetracker.service.impl;

import com.behrooz.expensetracker.entity.User;
import com.behrooz.expensetracker.exception.UserNotFoundException;
import com.behrooz.expensetracker.repository.UserRepository;
import com.behrooz.expensetracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;

  @Override
  public User findById(Integer id) {
    return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
  }

  @Override
  public User findByUsername(String username) {
    return userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
  }
}
