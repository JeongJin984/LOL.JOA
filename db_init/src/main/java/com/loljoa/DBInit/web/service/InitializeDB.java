package com.loljoa.DBInit.web.service;

import com.loljoa.DBInit.db.entity.*;
import com.loljoa.DBInit.db.repository.account.AccountRepository;
import com.loljoa.DBInit.db.repository.bettingChoice.BettingChoiceRepository;
import com.loljoa.DBInit.db.repository.bettingGame.BettingGameRepository;
import com.loljoa.DBInit.db.repository.bettingState.BettingStateRepository;
import com.loljoa.DBInit.db.repository.league.LeagueRepository;
import com.loljoa.DBInit.db.repository.tier.TierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class InitializeDB {
    private final TierRepository tierRepository;
    private final AccountRepository accountRepository;
    private final LeagueRepository leagueRepository;
    private final BettingGameRepository bettingGameRepository;
    private final BettingChoiceRepository bettingChoiceRepository;
    private final BettingStateRepository bettingStateRepository;
    private final Random random = new Random();

    @Transactional
    public void initData() {
        Tier bronze = new Tier("bronze");
        Tier silver = new Tier("silver");
        Tier gold = new Tier("gold");
        Tier platinum = new Tier("platinum");
        Tier diamond = new Tier("diamond");
        tierRepository.save(bronze);
        tierRepository.save(silver);
        tierRepository.save(gold);
        tierRepository.save(platinum);
        tierRepository.save(diamond);

        List<League> leagues = leagueRepository.findAll();
        List<BettingGame> games = new ArrayList<>();

        for(League v : leagues) {
            BettingGame g = new BettingGame("승부예측", v);
            games.add(g);
            bettingGameRepository.save(g);

            BettingChoice c1 = new BettingChoice(v.getLeagueName().split("vs")[0], g);
            BettingChoice c2 =new BettingChoice(v.getLeagueName().split("vs")[1], g);
            bettingChoiceRepository.save(c1);
            bettingChoiceRepository.save(c2);
        }
    }

    @Transactional
    public void InitTestData() {
        List<Tier> tiers = tierRepository.findAll();
        Account a1 = new Account("test1", "asdf", tiers.get(0), 1000000000L);
        Account a2 = new Account("test2", "asdf", tiers.get(1), 1000000000L);
        Account a3 = new Account("test3", "asdf", tiers.get(2), 1000000000L);
        Account a4 = new Account("test4", "asdf", tiers.get(3), 1000000000L);
        Account a5 = new Account("test5", "asdf", tiers.get(4), 1000000000L);
        accountRepository.save(a1);
        accountRepository.save(a2);
        accountRepository.save(a3);
        accountRepository.save(a4);
        accountRepository.save(a5);

//        List<BettingChoice> choices = bettingChoiceRepository.findAll();
//        List<League> leagues = leagueRepository.findAll();
//
//        int i=0;
//        for(BettingChoice v : choices) {
//            bettingStateRepository.save(new BettingState(Math.abs(random.nextLong()) % 1000, v, a1, leagues.get(0)));
//            bettingStateRepository.save(new BettingState(Math.abs(random.nextLong()) % 1000, v, a2, leagues.get(0)));
//            bettingStateRepository.save(new BettingState(Math.abs(random.nextLong()) % 1000, v, a3, leagues.get(0)));
//            bettingStateRepository.save(new BettingState(Math.abs(random.nextLong()) % 1000, v, a4, leagues.get(0)));
//            i++;
//            if(i == 20) break;
//        }
    }
}
