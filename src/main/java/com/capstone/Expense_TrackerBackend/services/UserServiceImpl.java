package com.capstone.Expense_TrackerBackend.services;

import com.capstone.Expense_TrackerBackend.dto.LoginRequest;
import com.capstone.Expense_TrackerBackend.dto.LoginResponse;
import com.capstone.Expense_TrackerBackend.entity.User;
import com.capstone.Expense_TrackerBackend.repository.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserServices {
    
    private final UserRepo userRepository;

    @Override
    public User registerUser(User user) {
        if (user.getUsername() == null || user.getPassword() == null || user.getEmail() == null) {
            throw new RuntimeException("Username, password and email are required");
        }

        // Check if username already exists
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        
        // Check if email already exists
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public LoginResponse loginUser(LoginRequest loginRequest) {
        if (loginRequest.getUsername() == null || loginRequest.getPassword() == null) {
            throw new RuntimeException("Username and password are required");
        }

        User user = userRepository.findByUsername(loginRequest.getUsername())
            .orElseThrow(() -> new EntityNotFoundException("User not found"));

        if (!user.getPassword().equals(loginRequest.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        
        LoginResponse response = new LoginResponse();
        response.setUserId(user.getId());
        response.setUsername(user.getUsername());
        response.setToken("dummy-token"); // You can add real JWT token generation here
        
        return response;
    }
}
