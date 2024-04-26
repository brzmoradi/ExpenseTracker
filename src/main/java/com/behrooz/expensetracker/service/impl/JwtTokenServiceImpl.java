package com.behrooz.expensetracker.service.impl;

import com.behrooz.expensetracker.dto.TokenResponse;
import com.behrooz.expensetracker.service.JwtTokenService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtTokenServiceImpl implements JwtTokenService {

  private long JWT_TOKEN_VALIDITY_TIME;

  private String secret;

  public JwtTokenServiceImpl(
      @Value("${jwt.secret}") String secret,
      @Value("${jwt.token.validity.time.second:7200}") long JWT_TOKEN_VALIDITY_TIME) {
    this.JWT_TOKEN_VALIDITY_TIME = JWT_TOKEN_VALIDITY_TIME;
    this.secret = secret;
  }

  private TokenResponse doGenerateToken(Map<String, Object> claims, Long tokenValidityInSecond) {
    TokenResponse tokenResponse = new TokenResponse();
    Date expiry = new Date(System.currentTimeMillis() + (tokenValidityInSecond * 1000));
    tokenResponse.setToken(
        Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(expiry)
            .signWith(SignatureAlgorithm.HS512, secret)
            .compact());
    tokenResponse.setExpiry(expiry);

    return tokenResponse;
  }
}
