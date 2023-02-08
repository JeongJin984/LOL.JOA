package com.loljoa.DBInit.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class League {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leagueId;

    private String weekNum;
    private String leagueName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @ManyToOne(targetEntity = LeagueCategory.class)
    private LeagueCategory category;

    @ManyToOne(targetEntity = GameTeam.class)
    private GameTeam leftTeam;

    @ManyToOne(targetEntity = GameTeam.class)
    private GameTeam rightTeam;

    public League(
            String weekNum,
            String leagueName,
            LocalDateTime startTime,
            LocalDateTime endTime,
            LeagueCategory category,
            GameTeam leftTeam,
            GameTeam rightTeam
    ) {
        this.weekNum = weekNum;
        this.leagueName = leagueName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.category = category;
        this.leftTeam = leftTeam;
        this.rightTeam = rightTeam;
    }

    @OneToMany(mappedBy = "league", fetch = FetchType.LAZY)
    private List<BettingGame> bettingGameData = new ArrayList<>();

    @OneToMany(mappedBy = "league", fetch = FetchType.LAZY)
    private List<LeagueComment> comments = new ArrayList<>();

    public League(String leagueName, LocalDateTime startTime, LocalDateTime endTime, LeagueCategory category) {
        this.leagueName = leagueName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.category = category;
    }
}
