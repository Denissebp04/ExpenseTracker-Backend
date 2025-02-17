package com.capstone.Expense_TrackerBackend.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ExpenseDTO {
    private Long id;
    private Long userId;
    private String title;
    private String category;  // Will be "FOOD", "TRANSPORT", etc.
    private LocalDate date;
    private BigDecimal amount;
}
