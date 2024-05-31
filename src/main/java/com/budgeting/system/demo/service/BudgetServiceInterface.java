package com.budgeting.system.demo.service;

import com.budgeting.system.demo.entity.BudgetEntity;
import com.budgeting.system.demo.models.BudgetPayload;

import java.util.List;

public interface BudgetServiceInterface {

    List<BudgetEntity> fetchAllBudgetByUserId(String userId);
    BudgetEntity getBudgetById(Long budgetId);

    List<Long> createBudget(BudgetPayload budgetPayload);

    void loadTransactions();

}
