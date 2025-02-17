package com.capstone.Expense_TrackerBackend.services;

import com.capstone.Expense_TrackerBackend.entity.User;
import com.capstone.Expense_TrackerBackend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepository;

    public User registerUser(User user) {
        // You can add additional logic here (e.g., checking if user already exists)
        return userRepository.save(user);
    }

    // TODO: Add login logic

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
