package com.budgeting.system.demo.repository;

import com.budgeting.system.demo.entity.Transactions;
import com.budgeting.system.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transactions,String> {
}
