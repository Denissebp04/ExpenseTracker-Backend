package com.capstone.Expense_TrackerBackend.services;

import com.capstone.Expense_TrackerBackend.dto.BudgetDTO;
import com.capstone.Expense_TrackerBackend.entity.Budget;
import com.capstone.Expense_TrackerBackend.entity.BudgetCategory;
import com.capstone.Expense_TrackerBackend.entity.User;
import com.capstone.Expense_TrackerBackend.repository.BudgetRepo;
import com.capstone.Expense_TrackerBackend.repository.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BudgetServiceImpl implements BudgetServices {

    private final BudgetRepo budgetRepo;
    private final UserRepo userRepo;

    @Override
    public BudgetDTO createBudget(BudgetDTO budgetDTO) {
        User user = userRepo.findById(budgetDTO.getUserId())
            .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + budgetDTO.getUserId()));

        Budget budget = new Budget();
        budget.setUser(user);
        budget.setCategory(BudgetCategory.valueOf(budgetDTO.getCategory()));
        budget.setAmount(budgetDTO.getAmount());
        budget.setSpent(budgetDTO.getSpent());

        Budget savedBudget = budgetRepo.save(budget);
        return convertToDTO(savedBudget);
    }

    @Override
    public List<BudgetDTO> getBudgetsByUserId(Long userId) {
        return budgetRepo.findByUserId(userId).stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    @Override
    public BudgetDTO updateBudget(Long id, BudgetDTO budgetDTO) {
        Budget budget = budgetRepo.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Budget not found with ID: " + id));

        budget.setCategory(BudgetCategory.valueOf(budgetDTO.getCategory()));
        budget.setAmount(budgetDTO.getAmount());
        budget.setSpent(budgetDTO.getSpent());

        Budget updatedBudget = budgetRepo.save(budget);
        return convertToDTO(updatedBudget);
    }

    @Override
    public void deleteBudget(Long id) {
        if (!budgetRepo.existsById(id)) {
            throw new EntityNotFoundException("Budget not found with ID: " + id);
        }
        budgetRepo.deleteById(id);
    }

    private BudgetDTO convertToDTO(Budget budget) {
        BudgetDTO dto = new BudgetDTO();
        dto.setId(budget.getId());
        dto.setUserId(budget.getUser().getId());
        dto.setCategory(budget.getCategory().toString());
        dto.setAmount(budget.getAmount());
        dto.setSpent(budget.getSpent());
        return dto;
    }
}
