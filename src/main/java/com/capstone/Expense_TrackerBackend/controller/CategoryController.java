package com.capstone.Expense_TrackerBackend.controller;

import com.capstone.Expense_TrackerBackend.entity.Category;
import com.capstone.Expense_TrackerBackend.services.CategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/category")
public class CategoryController {

    @Autowired
    private CategoryServices categoryServices;

    @PostMapping
    public Category addCategory(@RequestBody Category category){
        return categoryServices.save(category);
    }

    @GetMapping("/user/{userId}")
    public List<Category> getUserCategories(@PathVariable Long userId){
        return categoryServices.findByUserId(userId);
    }


}
