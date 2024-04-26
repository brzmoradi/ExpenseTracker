package com.behrooz.expensetracker.service;

import com.behrooz.expensetracker.config.security.OnlineUser;
import com.behrooz.expensetracker.dto.TokenInfoResponse;
import com.behrooz.expensetracker.dto.TokenResponse;

public interface TokenService {

  TokenInfoResponse getTokenInfo(String token);

  TokenResponse generateToken(OnlineUser onlineUser);
}
