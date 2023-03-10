package com.loljoa.DBInit.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class LeagueComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    private String content;

    @ManyToOne(targetEntity = League.class)
    private League league;

    @ManyToOne(targetEntity = Account.class)
    private Account commenter;

    public LeagueComment(String content, League league, Account boardCommenter) {
        this.content = content;
        this.league = league;
        this.commenter = boardCommenter;
    }
}
