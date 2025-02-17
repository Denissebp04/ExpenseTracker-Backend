package com.capstone.Expense_TrackerBackend.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class IncomeDTO {
    private Long id;
    private Long userId;
    private String title;
    private String category;
    private LocalDate date;
    private BigDecimal amount;
} 