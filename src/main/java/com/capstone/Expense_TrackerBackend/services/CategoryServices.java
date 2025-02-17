package com.capstone.Expense_TrackerBackend.services;

import com.capstone.Expense_TrackerBackend.entity.Category;

import java.util.List;

public interface CategoryServices {

    List<Category> findByUserId(Long userId);

    Category save(Category category);

    }
