package com.capstone.Expense_TrackerBackend.repository;

import com.capstone.Expense_TrackerBackend.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepo extends JpaRepository<Expense, Long> {
    List<Expense> findByUserId(Long userId);
}
