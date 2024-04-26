package com.behrooz.expensetracker.service.impl;

import com.behrooz.expensetracker.config.security.OnlineUser;
import com.behrooz.expensetracker.config.security.SecurityTokenConstant;
import com.behrooz.expensetracker.dto.TokenInfoResponse;
import com.behrooz.expensetracker.dto.TokenResponse;
import com.behrooz.expensetracker.exception.TokenNotValidException;
import com.behrooz.expensetracker.service.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TokenServiceImpl implements TokenService {

  private long JWT_TOKEN_VALIDITY_TIME;
  private Key secretKey;

  @Autowired
  public TokenServiceImpl(
      @Value("${jwt.secret}") String secret,
      @Value("${jwt.token.validity.time.second:7200}") long JWT_TOKEN_VALIDITY_TIME) {
    this.JWT_TOKEN_VALIDITY_TIME = JWT_TOKEN_VALIDITY_TIME;
    this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
  }

  @Override
  public TokenInfoResponse getTokenInfo(String token) {
    try {
      Claims allClaimsFromToken = getAllClaimsFromToken(token);
      return new TokenInfoResponse(
          (Integer) allClaimsFromToken.get(SecurityTokenConstant.IDENTIFIER_CLAIMS_NAME),
          (String) allClaimsFromToken.get(SecurityTokenConstant.USERNAME_CLAIMS_NAME),
          (List) allClaimsFromToken.get(SecurityTokenConstant.ROLES_CLAIMS_NAME));
    } catch (Exception e) {
      // todo : mybe roles of token is empty
      log.warn(e.getMessage());
      throw new TokenNotValidException();
    }
  }

  @Override
  public TokenResponse generateToken(OnlineUser onlineUser) {
    return doGenerateToken(
        onlineUser.getUsername(),
        createClaim(onlineUser.getId(), onlineUser.getUsername(), onlineUser.getRoles()));
  }

  private Claims getAllClaimsFromToken(String token) {
    try {
      return Jwts.parser().setSigningKey(secretKey).build().parseSignedClaims(token).getBody();
    } catch (Exception e) {
      log.warn(e.getMessage());
      throw new TokenNotValidException();
    }
  }

  private TokenResponse doGenerateToken(String username, Map<String, Object> claims) {
    TokenResponse tokenResponse = new TokenResponse();
    Date expiry = new Date(System.currentTimeMillis() + (JWT_TOKEN_VALIDITY_TIME * 1000));
    tokenResponse.setToken(
        Jwts.builder()
            .claims()
            .add(claims)
            .subject(username)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(expiry)
            .and()
            .signWith(secretKey)
            .compact());
    tokenResponse.setExpiry(expiry);
    return tokenResponse;
  }

  private Map<String, Object> createClaim(Integer id, String username, List<String> roles) {
    Map<String, Object> claims = new HashMap<>();
    claims.put(SecurityTokenConstant.IDENTIFIER_CLAIMS_NAME, id);
    claims.put(SecurityTokenConstant.USERNAME_CLAIMS_NAME, username);
    claims.put(SecurityTokenConstant.ROLES_CLAIMS_NAME, roles);
    return claims;
  }
}
