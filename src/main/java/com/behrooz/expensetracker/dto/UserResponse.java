package com.behrooz.expensetracker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private String username;
    private String password;
    private String title;
    private List<String> roles;
    private String createDate;
    private String jalaliCreateDate;
}
