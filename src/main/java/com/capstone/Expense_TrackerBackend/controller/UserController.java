package com.capstone.Expense_TrackerBackend.controller;

import com.capstone.Expense_TrackerBackend.entity.User;
import com.capstone.Expense_TrackerBackend.services.UserServices;
import com.capstone.Expense_TrackerBackend.dto.LoginRequest;
import com.capstone.Expense_TrackerBackend.dto.LoginResponse;
import com.capstone.Expense_TrackerBackend.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174", "http://localhost:5175"}, 
             allowCredentials = "true")
@RequiredArgsConstructor
public class UserController {

    private final UserServices userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            User registeredUser = userService.registerUser(user);
            return ResponseEntity.ok(registeredUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        try {
            System.out.println("Received login request for username: " + loginRequest.getUsername());
            LoginResponse response = userService.loginUser(loginRequest);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponse("Login failed", e.getMessage()));
        }
    }

    // TODO: Add login endpoint
}
