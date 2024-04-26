package com.behrooz.expensetracker.dto;

import java.util.Date;
import lombok.Data;

@Data
public class TokenResponse {
  private String Token;
  private Date expiry;
}
