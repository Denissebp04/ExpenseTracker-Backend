package com.capstone.Expense_TrackerBackend.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private Long userId;
    private String username;
    private String token;  // If you're using JWT
} 