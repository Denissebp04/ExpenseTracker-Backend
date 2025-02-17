package com.capstone.Expense_TrackerBackend.services;

import com.capstone.Expense_TrackerBackend.dto.ExpenseDTO;
import com.capstone.Expense_TrackerBackend.entity.Expense;
import com.capstone.Expense_TrackerBackend.entity.ExpenseCategory;
import com.capstone.Expense_TrackerBackend.entity.User;
import com.capstone.Expense_TrackerBackend.repository.ExpenseRepo;
import com.capstone.Expense_TrackerBackend.repository.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseServices {

    private final ExpenseRepo expenseRepository;
    private final UserRepo userRepo;

    @Override
    public ExpenseDTO postExpense(ExpenseDTO expenseDTO) {
        System.out.println("Received expense: " + expenseDTO);
        
        // Validate user exists
        User user = userRepo.findById(expenseDTO.getUserId())
            .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + expenseDTO.getUserId()));
        
        // Create new expense
        Expense expense = new Expense();
        expense.setUser(user);
        expense.setTitle(expenseDTO.getTitle());
        expense.setDate(expenseDTO.getDate());
        expense.setAmount(expenseDTO.getAmount());
        expense.setCategory(ExpenseCategory.valueOf(expenseDTO.getCategory()));

        // Save and convert back to DTO
        Expense savedExpense = expenseRepository.save(expense);
        System.out.println("Saved expense: " + savedExpense);
        
        return convertToDTO(savedExpense);
    }

    private ExpenseDTO convertToDTO(Expense expense) {
        ExpenseDTO dto = new ExpenseDTO();
        dto.setId(expense.getId());
        dto.setUserId(expense.getUser().getId());
        dto.setTitle(expense.getTitle());
        dto.setDate(expense.getDate());
        dto.setAmount(expense.getAmount());
        dto.setCategory(expense.getCategory().toString());
        return dto;
    }

    @Override
    public List<ExpenseDTO> getAllExpenses() {
        return expenseRepository.findAll().stream()
                .sorted(Comparator.comparing(Expense::getDate).reversed())
                .map(expense -> {
                    ExpenseDTO dto = new ExpenseDTO();
                    dto.setId(expense.getId());
                    dto.setTitle(expense.getTitle());
                    dto.setDate(expense.getDate());
                    dto.setAmount(expense.getAmount());
                    dto.setCategory(expense.getCategory().toString());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<ExpenseDTO> getAllExpensesByUserId(Long userId) {
        return expenseRepository.findByUserId(userId).stream()
                .map(expense -> {
                    ExpenseDTO dto = new ExpenseDTO();
                    dto.setId(expense.getId());
                    dto.setTitle(expense.getTitle());
                    dto.setAmount(expense.getAmount());
                    dto.setCategory(expense.getCategory().toString());
                    dto.setDate(expense.getDate());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public Expense updateExpense(Long id, ExpenseDTO expenseDTO) {
        Optional<Expense> optionalExpense = expenseRepository.findById(id);
        if (optionalExpense.isPresent()) {
            Expense expense = optionalExpense.get();
            
            // Validate user exists
            User user = userRepo.findById(expenseDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + expenseDTO.getUserId()));
            
            // Update expense fields
            expense.setUser(user);
            expense.setTitle(expenseDTO.getTitle());
            expense.setDate(expenseDTO.getDate());
            expense.setAmount(expenseDTO.getAmount());
            expense.setCategory(ExpenseCategory.valueOf(expenseDTO.getCategory()));

            // Save and return
            return expenseRepository.save(expense);
        } else {
            throw new EntityNotFoundException("Expense with id " + id + " NOT FOUND");
        }
    }

    public Expense getExpenseById(Long id) {
        Optional<Expense> optionalExpense = expenseRepository.findById(id);
        if (optionalExpense.isPresent()) {
            return optionalExpense.get();
        } else {
            throw new EntityNotFoundException("Expense with id" + " " + id + " " + "NOT FOUND");
        }
    }

    public void deleteExpense(Long id){
        Optional<Expense> optionalExpense = expenseRepository.findById(id);
        if(optionalExpense.isPresent()){
            expenseRepository.deleteById(id);
        }else{
            throw new EntityNotFoundException("Expense with id" + " " + " " + "NOT FOUND");
        }
    }
}

