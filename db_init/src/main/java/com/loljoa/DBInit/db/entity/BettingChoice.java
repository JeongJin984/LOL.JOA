package com.loljoa.DBInit.db.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
public class BettingChoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long choiceId;

    private String name;
    private Long totalPoint;
    private Long biggestPoint;
    private String biggestBetter;

    @ManyToOne(targetEntity = BettingGame.class)
    private BettingGame targetGame;

    public BettingChoice(String name, BettingGame targetGame) {
        this.name = name;
        this.targetGame = targetGame;
        this.totalPoint = 0L;
        this.biggestPoint = 0L;
        this.biggestBetter = "NONE";
    }

    @OneToMany(mappedBy = "choice", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<BettingState> bettingStates = new ArrayList<>();
}
