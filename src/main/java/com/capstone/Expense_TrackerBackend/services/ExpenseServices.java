package com.capstone.Expense_TrackerBackend.services;

import com.capstone.Expense_TrackerBackend.dto.ExpenseDTO;
import com.capstone.Expense_TrackerBackend.entity.Expense;

import java.util.List;


public interface ExpenseServices {

    ExpenseDTO postExpense(ExpenseDTO expenseDTO);

    List<ExpenseDTO> getAllExpenses();

    Expense getExpenseById(Long id);

    Expense updateExpense(Long id, ExpenseDTO expenseDTO);

    void deleteExpense(Long id);

    List<ExpenseDTO> getAllExpensesByUserId(Long userId);

}
