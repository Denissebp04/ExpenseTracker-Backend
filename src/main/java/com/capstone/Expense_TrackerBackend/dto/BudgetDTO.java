package com.capstone.Expense_TrackerBackend.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class BudgetDTO {
    private Long id;
    private Long userId;
    private String category;
    private BigDecimal amount;
    private BigDecimal spent;
}
