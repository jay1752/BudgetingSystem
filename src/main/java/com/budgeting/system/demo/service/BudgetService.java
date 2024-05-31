package com.budgeting.system.demo.service;

import com.budgeting.system.demo.entity.BudgetEntity;
import com.budgeting.system.demo.entity.Transactions;
import com.budgeting.system.demo.entity.UserEntity;
import com.budgeting.system.demo.models.Budget;
import com.budgeting.system.demo.models.BudgetPayload;
import com.budgeting.system.demo.repository.BudgetRepository;
import com.budgeting.system.demo.repository.TransactionRepository;
import com.budgeting.system.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BudgetService implements BudgetServiceInterface{

    @Autowired
    BudgetRepository budgetRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TransactionRepository transactionRepository;


    @Override
    public List<BudgetEntity> fetchAllBudgetByUserId(String userId) {

        return budgetRepository.findByUsersUuid(userId);
    }

    @Override
    public BudgetEntity getBudgetById(Long budgetId) {
        Optional<BudgetEntity> budget = budgetRepository.findById(budgetId);
        return budget.orElse(null);

    }

    @Override
    public List<Long> createBudget(BudgetPayload budgetPayload) {
        // adding all users related to budget
        List<String> allUsers = budgetPayload.getRelativeId();
        allUsers.add(budgetPayload.getUserId());

        List<UserEntity> users = userRepository.findAllById(allUsers);

        List<BudgetEntity> budgetEntities = transformBudgetToBudgetEntity(budgetPayload.getBudgets(),users);

        List<BudgetEntity> savedBudgets = budgetRepository.saveAll(budgetEntities);

        return savedBudgets.stream().map(BudgetEntity::getBudgetId).toList();
    }

    @Override
    public  void loadTransactions(){
        //can be implemented sizing here and also Aggregation logic instead running transaction one by one
        List<Transactions> transactionsList = transactionRepository.findAll();

        List<BudgetEntity> budgetEntities = new ArrayList<>();
        transactionsList.forEach(transactions -> {
            String userId  = transactions.getUserId();
            UserEntity userEntity = userRepository.findById(userId).orElseThrow();
            BudgetEntity budgetEntity = budgetRepository.findBudgetByUsersUuidAndFinancialCategory( userId,
                    transactions.getTransactionCategory());

            // If expanses are in different currency , conversion can be done
            //if(!transactions.getTransactionCategory().equalsIgnoreCase(budgetEntity.getFinancialCategory()))

            // Budget date and transaction date com
            Timestamp transactionDate = transactions.getTransactionTime();
            Timestamp budgetStartDate = budgetEntity.getStartDate();
            Timestamp budgetEndDate  =  budgetEntity.getEndDate();

            // Check if timestampToCheck is between startTime and endTime
            boolean isBetween = transactionDate.after(budgetStartDate) && transactionDate.before(budgetEndDate);

            if(isBetween){
                double expanse = budgetEntity.getTotalExpenses()+transactions.getAmount();
                budgetEntity.setTotalExpenses(expanse);
                budgetEntities.add(budgetEntity);
            }else{
                System.out.println("Transaction with Id = "+transactions.getTransactionId()+" lies beyond Budget Dates. Skipping update for this transaction");
            }


        });

        budgetRepository.saveAll(budgetEntities);
    }

    private List<BudgetEntity> transformBudgetToBudgetEntity(List<Budget> budgets,List<UserEntity> users){
        List<BudgetEntity> budgetEntities = new ArrayList<>();

        for(Budget budget : budgets){
            BudgetEntity budgetEntity = new BudgetEntity();
            budgetEntity.setTotalBudgetAmount(budget.getTotalBudgetAmount());
            budgetEntity.setTotalExpenses(0.0);
            budgetEntity.setCurrency(budget.getCurrency());
            budgetEntity.setUsers(users);
            budgetEntity.setFinancialCategory(budget.getFinancialCategory().name());
            budgetEntity.setStartDate(new Timestamp(budget.getStartDate().getTime()));
            budgetEntity.setEndDate(new Timestamp(budget.getEndDate().getTime()));
            budgetEntities.add(budgetEntity);
        }

        return budgetEntities;
    }
}
