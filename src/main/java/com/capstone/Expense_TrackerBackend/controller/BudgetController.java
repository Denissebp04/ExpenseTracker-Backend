package com.capstone.Expense_TrackerBackend.controller;

import com.capstone.Expense_TrackerBackend.dto.BudgetDTO;
import com.capstone.Expense_TrackerBackend.services.BudgetServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/budgets")
@CrossOrigin(origins = "http://localhost:5175")
@RequiredArgsConstructor
public class BudgetController {
    private final BudgetServices budgetServices;

    @PostMapping
    public ResponseEntity<BudgetDTO> createBudget(@RequestBody BudgetDTO dto) {
        System.out.println("Creating budget: " + dto);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(budgetServices.createBudget(dto));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BudgetDTO>> getUserBudgets(@PathVariable Long userId) {
        return ResponseEntity.ok(budgetServices.getBudgetsByUserId(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BudgetDTO> updateBudget(@PathVariable Long id, @RequestBody BudgetDTO dto) {
        return ResponseEntity.ok(budgetServices.updateBudget(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBudget(@PathVariable Long id) {
        budgetServices.deleteBudget(id);
        return ResponseEntity.noContent().build();
    }
}
