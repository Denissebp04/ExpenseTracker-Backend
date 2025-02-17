package com.capstone.Expense_TrackerBackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/test")
public class TestController {
    @GetMapping
    public String test() {
        System.out.println("Test endpoint hit!");
        return "Server is running!";
    }
} 