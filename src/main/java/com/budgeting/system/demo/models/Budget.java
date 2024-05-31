package com.budgeting.system.demo.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Budget implements Serializable {
    @NotNull
    @NotEmpty
    Double totalBudgetAmount;
    @NotNull
    @NotEmpty
    String currency;
    @NotNull
    @NotEmpty
    FinancialCategory financialCategory;
    @NotNull
    @NotEmpty
    Date startDate;
    @NotNull
    @NotEmpty
    Date endDate;

}


