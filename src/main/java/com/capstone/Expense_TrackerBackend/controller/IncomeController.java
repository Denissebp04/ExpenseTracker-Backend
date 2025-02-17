package com.capstone.Expense_TrackerBackend.controller;

import com.capstone.Expense_TrackerBackend.dto.IncomeDTO;
import com.capstone.Expense_TrackerBackend.services.IncomeServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incomes")
@CrossOrigin(origins = "http://localhost:5175")
@RequiredArgsConstructor
public class IncomeController {
    private final IncomeServices incomeServices;

    @PostMapping
    public ResponseEntity<IncomeDTO> createIncome(@RequestBody IncomeDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(incomeServices.createIncome(dto));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<IncomeDTO>> getUserIncomes(@PathVariable Long userId) {
        return ResponseEntity.ok(incomeServices.getAllIncomesByUserId(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<IncomeDTO> updateIncome(@PathVariable Long id, @RequestBody IncomeDTO dto) {
        return ResponseEntity.ok(incomeServices.updateIncome(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIncome(@PathVariable Long id) {
        incomeServices.deleteIncome(id);
        return ResponseEntity.noContent().build();
    }
} 