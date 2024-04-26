package com.behrooz.expensetracker.service;

import com.behrooz.expensetracker.dto.LoginRequest;
import com.behrooz.expensetracker.dto.RegisterUserRequest;
import com.behrooz.expensetracker.dto.TokenResponse;
import com.behrooz.expensetracker.entity.User;
import jakarta.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import org.springframework.util.DigestUtils;

public interface UserService {

  User findById(Integer id);

  User findByUsername(String username);

  User registerUser(RegisterUserRequest request);

  TokenResponse login(LoginRequest request);

  default String md5EncryptPassword(String input) {
    return DatatypeConverter.printHexBinary(
        DigestUtils.md5Digest(input.getBytes(StandardCharsets.UTF_8)));
  }
}
