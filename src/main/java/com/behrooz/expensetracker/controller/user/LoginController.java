package com.behrooz.expensetracker.controller.user;

import com.behrooz.expensetracker.dto.LoginRequest;
import com.behrooz.expensetracker.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

  @PostMapping(value = "${apis.open}/login")
  public TokenResponse login(@RequestBody LoginRequest request) {
    return new TokenResponse();
  }
}
