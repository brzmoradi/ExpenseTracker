package com.behrooz.expensetracker.controller.user;

import com.behrooz.expensetracker.dto.LoginRequest;
import com.behrooz.expensetracker.dto.TokenResponse;
import com.behrooz.expensetracker.service.UserService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {
  private final UserService userService;

  @PostMapping(value = "${apis.open}/login")
  public TokenResponse login(@RequestBody @NotNull @Validated LoginRequest request) {
    return userService.login(request);
  }
}
