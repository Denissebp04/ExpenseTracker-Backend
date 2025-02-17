package com.capstone.Expense_TrackerBackend.services;

import com.capstone.Expense_TrackerBackend.dto.IncomeDTO;
import java.util.List;

public interface IncomeServices {
    IncomeDTO createIncome(IncomeDTO incomeDTO);
    List<IncomeDTO> getAllIncomesByUserId(Long userId);
    IncomeDTO updateIncome(Long id, IncomeDTO incomeDTO);
    void deleteIncome(Long id);
} 