package com.loljoa.DBInit.db.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class BettingState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long stateId;

    private Long point;

    @ManyToOne(targetEntity = BettingChoice.class, fetch = FetchType.LAZY)
    private BettingChoice choice;

    @ManyToOne(targetEntity = Account.class, fetch = FetchType.LAZY)
    private Account better;

    @ManyToOne(targetEntity = League.class)
    private League league;

    public BettingState(Long point, BettingChoice choice, Account better, League league) {
        this.point = point;
        this.choice = choice;
        this.better = better;
        this.league = league;
    }
}
