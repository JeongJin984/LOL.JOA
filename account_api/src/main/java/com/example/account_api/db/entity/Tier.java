package com.example.account_api.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Tier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tierId;

    String name;

    @OneToMany(mappedBy = "tier")
    List<Account> account = new ArrayList<>();

    public Tier(String name) {
        this.name = name;
    }
}