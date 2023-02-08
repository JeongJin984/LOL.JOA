package com.loljoa.DBInit.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class BettingGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gameId;

    private String title;
    private Long totalPoint;

    @ManyToOne(targetEntity = League.class)
    private League league;

    public BettingGame(String title, League league) {
        this.title = title;
        this.league = league;
        this.totalPoint = 0L;
    }

    @OneToMany(mappedBy = "targetGame")
    private List<BettingChoice> choices = new ArrayList<>();
}
