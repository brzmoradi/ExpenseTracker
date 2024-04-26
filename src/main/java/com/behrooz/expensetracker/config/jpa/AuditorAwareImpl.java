package com.behrooz.expensetracker.config.jpa;

import com.behrooz.expensetracker.config.security.OnlineUser;
import java.util.Optional;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuditorAwareImpl implements AuditorAware<Integer> {

  @Override
  public Optional<Integer> getCurrentAuditor() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null) {
      return Optional.empty();
    } else {
      Object principal = authentication.getPrincipal();
      return principal instanceof OnlineUser
          ? Optional.of(((OnlineUser) principal).getId())
          : Optional.empty();
    }
  }
}
