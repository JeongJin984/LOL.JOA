package com.loljoa.server.db.repository.league;

import com.loljoa.server.db.condition.LeagueSearchCondition;
import com.loljoa.server.db.entity.League;

import java.util.List;

public interface LeagueRepositoryCustom {
    List<League> getLeagueScheduleList(LeagueSearchCondition condition);

    League getSingleLeagueSchedule(LeagueSearchCondition condition);
}