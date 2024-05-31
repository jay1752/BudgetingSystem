package com.budgeting.system.demo.controller;

import com.budgeting.system.demo.entity.BudgetEntity;
import com.budgeting.system.demo.models.BudgetPayload;
import com.budgeting.system.demo.service.BudgetService;
import jakarta.validation.Valid;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
public class BudgetController {

    @Autowired
    BudgetService budgetService;

    @PostMapping("/budget")
    ResponseEntity<?> createBudget(@Valid @RequestBody BudgetPayload budgetPayload){
        List<Long> budgetIds = budgetService.createBudget(budgetPayload);
        return ResponseEntity.ok("Budgets created with budgetIds : "+budgetIds);


    }
    @GetMapping("/budget/user/{userId}")
    ResponseEntity<?> getAllBudgetsByUserId(@PathVariable String userId){
        List<BudgetEntity> budgetEntities = budgetService.fetchAllBudgetByUserId(userId);
        return ResponseEntity.ok(budgetEntities);
    }

    @GetMapping("/budget/{budgetId}")
    ResponseEntity<?> getAllBudgetsByBudgetId(@PathVariable Long budgetId){
        BudgetEntity budgetEntity = budgetService.getBudgetById(budgetId);
        return ResponseEntity.ok(budgetEntity);
    }


    @PostMapping("/expanse/load")
    ResponseEntity<?> createBudget(){
        budgetService.loadTransactions();
        return ResponseEntity.ok("Successfully loaded all transaction to System");
    }



}
