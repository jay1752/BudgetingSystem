package com.budgeting.system.demo.repository;

import com.budgeting.system.demo.entity.BudgetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BudgetRepository extends JpaRepository<BudgetEntity,Long> {

    BudgetEntity findBudgetByUsersUuidAndFinancialCategory(String userId,String financialCategory);
    List<BudgetEntity> findByUsersUuid(String userId);

}

