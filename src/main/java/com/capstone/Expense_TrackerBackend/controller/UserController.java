package com.capstone.Expense_TrackerBackend.controller;

import com.capstone.Expense_TrackerBackend.dto.LoginRequest;
import com.capstone.Expense_TrackerBackend.dto.LoginResponse;
import com.capstone.Expense_TrackerBackend.entity.User;
import com.capstone.Expense_TrackerBackend.services.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174", "http://localhost:5175"})
@RequiredArgsConstructor
public class UserController {
    
    private final UserServices userServices;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            User savedUser = userServices.registerUser(user);
            return ResponseEntity.ok(savedUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        try {
            LoginResponse response = userServices.loginUser(loginRequest);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
