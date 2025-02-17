package com.capstone.Expense_TrackerBackend.services;

import com.capstone.Expense_TrackerBackend.dto.LoginRequest;
import com.capstone.Expense_TrackerBackend.dto.LoginResponse;
import com.capstone.Expense_TrackerBackend.entity.User;
import java.util.Optional;

public interface UserServices {
    User registerUser(User user);
    Optional<User> findByUsername(String username);
    LoginResponse loginUser(LoginRequest loginRequest);
} 