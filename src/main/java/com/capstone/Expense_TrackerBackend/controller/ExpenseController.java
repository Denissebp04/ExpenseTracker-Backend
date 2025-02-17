package com.capstone.Expense_TrackerBackend.controller;

import com.capstone.Expense_TrackerBackend.dto.ExpenseDTO;
import com.capstone.Expense_TrackerBackend.services.ExpenseServices;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseServices expenseServices;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ExpenseDTO>> getUserExpenses(@PathVariable Long userId) {
        System.out.println("Fetching expenses for user: " + userId);
        try {
            List<ExpenseDTO> expenses = expenseServices.getAllExpensesByUserId(userId);
            System.out.println("Found " + expenses.size() + " expenses for user " + userId);
            return ResponseEntity.ok(expenses);
        } catch (Exception e) {
            System.err.println("Error fetching expenses: " + e.getMessage());
            throw e;
        }
    }

    @PostMapping
    public ResponseEntity<ExpenseDTO> createExpense(@RequestBody ExpenseDTO dto) {
        System.out.println("Creating expense: " + dto);
        try {
            System.out.println("User ID from request: " + dto.getUserId());
            
            ExpenseDTO createdExpense = expenseServices.postExpense(dto);
            System.out.println("Successfully created expense: " + createdExpense);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdExpense);
        } catch (Exception e) {
            System.err.println("Error creating expense: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<ExpenseDTO>> getAllExpenses() {
        System.out.println("Getting all expenses");
        return ResponseEntity.ok(expenseServices.getAllExpenses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getExpenseById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(expenseServices.getExpenseById(id));
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Expense not found: " + ex.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving expense.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateExpense(@PathVariable Long id, @RequestBody ExpenseDTO dto) {
        try {
            return ResponseEntity.ok(expenseServices.updateExpense(id, dto));
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Expense not found: " + ex.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating expense.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExpense(@PathVariable Long id) {
        try {
            expenseServices.deleteExpense(id);
            return ResponseEntity.ok("Expense deleted successfully.");
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Expense not found: " + ex.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting expense.");
        }
    }
}
