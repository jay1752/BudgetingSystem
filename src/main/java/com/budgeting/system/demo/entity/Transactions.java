package com.budgeting.system.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name="TRANSACTIONS")
public class Transactions {

    @Id
    Long transactionId;
    //relations can be attached // ignored because loading through sql
//    @JoinColumn(name = "uuid")
//    @ManyToOne(fetch = FetchType.LAZY)
//    UserEntity userEntity;

    String userId;

    Double amount;

    String currency;

    Timestamp transactionTime;

    String transactionCategory;


}
