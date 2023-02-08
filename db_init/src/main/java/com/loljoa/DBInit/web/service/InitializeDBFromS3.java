package com.loljoa.DBInit.web.service;

import com.loljoa.DBInit.common.service.S3Service;
import com.loljoa.DBInit.db.entity.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class InitializeDBFromS3 {
    private final EntityManagerFactory emf;
    private final S3Service s3Service;

    public void initLCKSchedule() throws IOException {
        log.info("initialize Started");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tr = em.getTransaction();

        tr.begin();
        LeagueCategory category1 = new LeagueCategory("lck");
        LeagueCategory category2 = new LeagueCategory("자낳대");
        em.persist(category1);
        em.persist(category2);
        Map<String, Object> lckSchedule = s3Service.getLCKSchedule("schedule.json");
        for(String weekNum : lckSchedule.keySet()) {
            Map<String, Object> schedules = (HashMap)lckSchedule.get(weekNum);
            for(String time : schedules.keySet()) {
                List<LinkedHashMap> list = (List)schedules.get(time);
                Boolean toggle = true;
                for(LinkedHashMap map : list) {
                    String leftTeamName = (String)map.get("left_team_name");
                    String rightTeamName = (String)map.get("right_team_name");

                    List leftTeamList = em.createQuery("select t from GameTeam t where t.name = :name ")
                            .setParameter("name", leftTeamName)
                            .getResultList();

                    List rightTeamList = em.createQuery("select t from GameTeam t where t.name = :name ")
                            .setParameter("name", rightTeamName)
                            .getResultList();

                    GameTeam leftTeam = null;
                    GameTeam rightTeam = null;
                    if(leftTeamList.isEmpty()) {
                        leftTeam = new GameTeam(leftTeamName);
                        em.persist(leftTeam);
                    } else {
                        leftTeam = (GameTeam) leftTeamList.get(0);
                    }

                    if(rightTeamList.isEmpty()) {
                        rightTeam = new GameTeam(rightTeamName);
                        em.persist(rightTeam);
                    } else {
                        rightTeam = (GameTeam) rightTeamList.get(0);
                    }

                    String longTime;
                    if(toggle) {
                        longTime = time.substring(4) + " 05:00";
                    } else {
                        longTime = time.substring(4) + " 08:00";
                    }
                    League league = new League(
                            weekNum,
                            map.get("left_team_name") + "vs" + map.get("right_team_name"),
                            LocalDateTime.parse(longTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                            null,
                            category1,
                            leftTeam,
                            rightTeam
                    );
                    em.persist(league);

                    if(league.getLeagueName().equals("T1vsDRX")) {
                        BettingGame teamPredictBettingGame = new BettingGame(league.getLeagueName(), league);
                        em.persist(teamPredictBettingGame);
                        BettingChoice teamChoice1 = new BettingChoice(league.getLeftTeam().getName(), teamPredictBettingGame);
                        BettingChoice teamChoice2 = new BettingChoice(league.getRightTeam().getName(), teamPredictBettingGame);
                        em.persist(teamChoice1);
                        em.persist(teamChoice2);

                        BettingGame dragonSoulPredictBettingGame = new BettingGame("용의 영혼", league);
                        em.persist(dragonSoulPredictBettingGame);
                        BettingChoice dragonChoice1 = new BettingChoice("불", dragonSoulPredictBettingGame);
                        BettingChoice dragonChoice2 = new BettingChoice("바람", dragonSoulPredictBettingGame);
                        BettingChoice dragonChoice3 = new BettingChoice("바다", dragonSoulPredictBettingGame);
                        BettingChoice dragonChoice4 = new BettingChoice("대지", dragonSoulPredictBettingGame);
                        BettingChoice dragonChoice5 = new BettingChoice("마법 공학", dragonSoulPredictBettingGame);
                        em.persist(dragonChoice1);
                        em.persist(dragonChoice2);
                        em.persist(dragonChoice3);
                        em.persist(dragonChoice4);
                        em.persist(dragonChoice5);
                    }

                    toggle = !toggle;
                }
            }
        }
        em.flush();
        tr.commit();
        em.close();
        log.info("initialize End");
    }
}
