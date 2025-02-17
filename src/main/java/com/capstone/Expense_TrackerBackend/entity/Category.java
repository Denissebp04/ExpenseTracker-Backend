package com.capstone.Expense_TrackerBackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public enum ExpenseCategory {
        FOOD,
        TRANSPORTATION,
        ENTERTAINMENT,
        UTILITIES,
        OTHERS
        // Add other categories as needed
    }
}
