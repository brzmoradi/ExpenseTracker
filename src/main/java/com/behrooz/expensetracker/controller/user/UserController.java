package com.behrooz.expensetracker.controller.user;

import com.behrooz.expensetracker.dto.RegisterUserRequest;
import com.behrooz.expensetracker.dto.UserResponse;
import com.behrooz.expensetracker.entity.User;
import com.behrooz.expensetracker.service.UserService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PostMapping(value = "${apis.secure}/user")
  public UserResponse register(@RequestBody @NotNull @Validated RegisterUserRequest request) {
    User user = userService.registerUser(request);
    return UserResponse.builder()
        .username(user.getUsername())
        .password(user.getSavedPassword())
        .title(user.getTitle())
        .roles(user.getRolesListString())
        .createDate(user.getCreatedDate().toString()) // todo: formatting return date
        .build();
  }
}
