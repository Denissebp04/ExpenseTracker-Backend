package com.capstone.Expense_TrackerBackend.repository;

import com.capstone.Expense_TrackerBackend.entity.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BudgetRepo extends JpaRepository<Budget, Long> {
    List<Budget> findByUserId(Long userId);
}
