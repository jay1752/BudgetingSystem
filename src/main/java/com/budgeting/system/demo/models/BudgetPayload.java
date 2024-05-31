package com.budgeting.system.demo.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class BudgetPayload implements Serializable {
    @NotNull
    @NotEmpty
    String userId;
    @NotNull
    @NotEmpty
    List<String> relativeId;
    @NotNull
    @NotEmpty
    List<Budget> budgets;

}

