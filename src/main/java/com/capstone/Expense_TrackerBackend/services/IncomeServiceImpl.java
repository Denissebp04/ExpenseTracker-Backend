package com.capstone.Expense_TrackerBackend.services;

import com.capstone.Expense_TrackerBackend.dto.IncomeDTO;
import com.capstone.Expense_TrackerBackend.entity.Income;
import com.capstone.Expense_TrackerBackend.entity.IncomeCategory;
import com.capstone.Expense_TrackerBackend.entity.User;
import com.capstone.Expense_TrackerBackend.repository.IncomeRepo;
import com.capstone.Expense_TrackerBackend.repository.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IncomeServiceImpl implements IncomeServices {
    
    private final IncomeRepo incomeRepo;
    private final UserRepo userRepo;

    @Override
    public IncomeDTO createIncome(IncomeDTO incomeDTO) {
        User user = userRepo.findById(incomeDTO.getUserId())
            .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + incomeDTO.getUserId()));

        Income income = new Income();
        income.setUser(user);
        income.setTitle(incomeDTO.getTitle());
        income.setAmount(incomeDTO.getAmount());
        income.setDate(incomeDTO.getDate());
        income.setCategory(IncomeCategory.valueOf(incomeDTO.getCategory()));

        Income savedIncome = incomeRepo.save(income);
        return convertToDTO(savedIncome);
    }

    @Override
    public List<IncomeDTO> getAllIncomesByUserId(Long userId) {
        return incomeRepo.findByUserId(userId).stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    @Override
    public IncomeDTO updateIncome(Long id, IncomeDTO incomeDTO) {
        Income income = incomeRepo.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Income not found with ID: " + id));

        User user = userRepo.findById(incomeDTO.getUserId())
            .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + incomeDTO.getUserId()));

        income.setUser(user);
        income.setTitle(incomeDTO.getTitle());
        income.setAmount(incomeDTO.getAmount());
        income.setDate(incomeDTO.getDate());
        income.setCategory(IncomeCategory.valueOf(incomeDTO.getCategory()));

        Income updatedIncome = incomeRepo.save(income);
        return convertToDTO(updatedIncome);
    }

    @Override
    public void deleteIncome(Long id) {
        if (!incomeRepo.existsById(id)) {
            throw new EntityNotFoundException("Income not found with ID: " + id);
        }
        incomeRepo.deleteById(id);
    }

    private IncomeDTO convertToDTO(Income income) {
        IncomeDTO dto = new IncomeDTO();
        dto.setId(income.getId());
        dto.setUserId(income.getUser().getId());
        dto.setTitle(income.getTitle());
        dto.setAmount(income.getAmount());
        dto.setDate(income.getDate());
        dto.setCategory(income.getCategory().toString());
        return dto;
    }
} 