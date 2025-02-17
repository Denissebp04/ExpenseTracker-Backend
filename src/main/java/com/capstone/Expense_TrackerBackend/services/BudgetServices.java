package com.capstone.Expense_TrackerBackend.services;

import com.capstone.Expense_TrackerBackend.dto.BudgetDTO;
import java.util.List;

public interface BudgetServices {
    BudgetDTO createBudget(BudgetDTO budgetDTO);
    List<BudgetDTO> getBudgetsByUserId(Long userId);
    BudgetDTO updateBudget(Long id, BudgetDTO budgetDTO);
    void deleteBudget(Long id);
}
