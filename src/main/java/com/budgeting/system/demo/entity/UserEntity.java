package com.budgeting.system.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "USER_ENTITY")
@Data
public class UserEntity {
    @Id
    String uuid;

}
