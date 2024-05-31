package com.budgeting.system.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name="Budget")
@Data
public class BudgetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long budgetId;

    Double totalBudgetAmount;

    Double totalExpenses;

    String currency;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "budget_user",
            joinColumns = @JoinColumn(name = "budgetId"),
            inverseJoinColumns = @JoinColumn(name = "uuid")
    )
    List<UserEntity> users;

    String financialCategory;

    Timestamp startDate;

    Timestamp endDate;

}

