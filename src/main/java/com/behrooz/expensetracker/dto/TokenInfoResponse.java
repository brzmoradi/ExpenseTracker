package com.behrooz.expensetracker.dto;

import java.util.List;

public record TokenInfoResponse(int id, String username, List<String> roles) {}
