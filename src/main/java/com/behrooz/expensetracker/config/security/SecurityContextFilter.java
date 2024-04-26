package com.behrooz.expensetracker.config.security;

import com.behrooz.expensetracker.dto.TokenInfoResponse;
import com.behrooz.expensetracker.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class SecurityContextFilter extends OncePerRequestFilter {

  private final TokenService tokenService;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    String authorizationHeader = request.getHeader("Authorization");
    if (authorizationHeader != null) {
      String bearerToken = getBearerToken(authorizationHeader);
      addAuthenticationObjectIntoContext(bearerToken);
    }
    filterChain.doFilter(request, response);
  }

  private String getBearerToken(String token) {
    String[] s = token.split(" ");
    if (s.length != 2 || !s[0].equalsIgnoreCase("bearer")) {
      return null;
    }
    return s[1];
  }

  private void addAuthenticationObjectIntoContext(String token) {
    TokenInfoResponse tokenInfoResponse = tokenService.getTokenInfo(token);
    OnlineUser onlineUser =
        new OnlineUser(
            tokenInfoResponse.id(),
            tokenInfoResponse.username(),
            tokenInfoResponse.roles()); // user.getUser(),
    SecurityContextHolder.getContext()
        .setAuthentication(
            new UsernamePasswordAuthenticationToken(onlineUser, null, onlineUser.getAuthorities()));
  }
}
