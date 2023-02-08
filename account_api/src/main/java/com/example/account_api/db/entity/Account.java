package com.example.account_api.db.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long accountId;

    @Column(unique = true)
    String username;
    String password;

    Long point;

    @ManyToOne(targetEntity = Tier.class)
    Tier tier;

    public Account(Long accountId) {
        this.accountId = accountId;
    }

    public Account(String username, String password, Tier tier, Long point) {
        this.username = username;
        this.password = password;
        this.tier = tier;
        this.point = point;
    }
}