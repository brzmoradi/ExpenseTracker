package com.behrooz.expensetracker.service.impl;

import com.behrooz.expensetracker.config.security.OnlineUser;
import com.behrooz.expensetracker.dto.LoginRequest;
import com.behrooz.expensetracker.dto.RegisterUserRequest;
import com.behrooz.expensetracker.dto.TokenResponse;
import com.behrooz.expensetracker.entity.User;
import com.behrooz.expensetracker.exception.InvalidUserNameException;
import com.behrooz.expensetracker.exception.UserCredentialException;
import com.behrooz.expensetracker.exception.UserNotFoundException;
import com.behrooz.expensetracker.exception.model.UserAlreadyExistException;
import com.behrooz.expensetracker.repository.UserRepository;
import com.behrooz.expensetracker.service.RoleService;
import com.behrooz.expensetracker.service.TokenService;
import com.behrooz.expensetracker.service.UserService;
import com.behrooz.expensetracker.util.StringUtil;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;

  private final TokenService tokenService;

  private final RoleService roleService;

  @Override
  public User findById(Integer id) {
    return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
  }

  @Override
  public User findByUsername(String username) {
    return userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
  }

  @Transactional
  @Override
  public TokenResponse login(LoginRequest request) {
    User user =
        userRepository.findByUsername(request.user()).orElseThrow(UserCredentialException::new);
    if (!user.getPassword().equals(md5EncryptPassword(request.password()))) {
      throw new UserCredentialException();
    }
    user.setLastLoginDate(LocalDateTime.now());
    return tokenService.generateToken(
        new OnlineUser(user.getId(), user.getUsername(), user.getRolesListString()));
  }

  @Transactional
  @Override
  public User registerUser(RegisterUserRequest request) {
    if (!StringUtil.USER_NAME_REGEX_PATTERN.matcher(request.getUsername()).matches()) {
      throw new InvalidUserNameException();
    }
    if (userRepository.existsByUsername(request.getUsername())) {
      throw new UserAlreadyExistException();
    }
    return registerUser(request, StringUtil.generatePassword(6));
  }

  private User registerUser(RegisterUserRequest request, String password) {
    User user = new User();
    user.setUsername(request.getUsername());
    user.setTitle(request.getTitle());
    user.setSavedPassword(password);
    user.setPassword(md5EncryptPassword(password));
    user.setRoles(roleService.get(request.getRoleIds()));
    return userRepository.save(user);
  }
}
