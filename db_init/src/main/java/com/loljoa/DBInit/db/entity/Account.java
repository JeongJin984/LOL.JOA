package com.loljoa.DBInit.db.entity;

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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long accountId;

    @Column(unique = true)
    String username;
    String password;

    Long point;

    @ManyToOne(targetEntity = Tier.class)
    Tier tier;

    @OneToMany(mappedBy = "better")
    List<BettingState> bettingStates = new ArrayList<>();

    @OneToMany(mappedBy = "writer")
    List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "commenter")
    List<PostComment> postComments = new ArrayList<>();

    @OneToMany(mappedBy = "commenter")
    List<LeagueComment> leagueComments = new ArrayList<>();

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
