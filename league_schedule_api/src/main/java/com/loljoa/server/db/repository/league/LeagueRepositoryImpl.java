package com.loljoa.server.db.repository.league;

import com.loljoa.server.db.condition.LeagueSearchCondition;
import com.loljoa.server.db.entity.League;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.loljoa.server.db.entity.QGameTeam.gameTeam;
import static com.loljoa.server.db.entity.QLeague.league;
import static com.loljoa.server.db.entity.QLeagueCategory.leagueCategory;
import static org.springframework.util.StringUtils.hasText;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class LeagueRepositoryImpl implements LeagueRepositoryCustom {
    private final JPAQueryFactory factory;

    @Override
    public List<League> getLeagueScheduleList(LeagueSearchCondition condition) {
        List<League> fetch = factory
                .selectFrom(league)
                .join(league.category(), leagueCategory).fetchJoin()
                .join(league.rightTeam(), gameTeam).fetchJoin()
                .join(league.leftTeam(), gameTeam).fetchJoin()
                .where(
                        leagueCategoryEq(condition.getCategoryName()),
                        leagueWeekNumEq(condition.getWeekNum()),
                        leftTeamNameEq(condition.getLeftTeamName()),
                        rightTeamNameEq(condition.getRightTeamName())
                )
                .fetch();
        return fetch;
    }

    @Override
    public League getSingleLeagueSchedule(LeagueSearchCondition condition) {
        League fetch = factory
                .selectFrom(league)
                .join(league.category(), leagueCategory).fetchJoin()
                .join(league.rightTeam(), gameTeam).fetchJoin()
                .join(league.leftTeam(), gameTeam).fetchJoin()
                .where(
                        leagueCategoryEq(condition.getCategoryName()),
                        leagueWeekNumEq(condition.getWeekNum()),
                        leftTeamNameEq(condition.getLeftTeamName()),
                        rightTeamNameEq(condition.getRightTeamName())
                )
                .fetchFirst();
        return fetch;
    }

    BooleanExpression leagueCategoryEq(String categoryName) {
        return hasText(categoryName) ? league.category().name.eq(categoryName) : null;
    }

    BooleanExpression leagueWeekNumEq(String weekNum) {
        return hasText(weekNum) ? league.weekNum.eq(weekNum) : null;
    }

    BooleanExpression leftTeamNameEq(String teamName) {
        return hasText(teamName) ? league.leftTeam().name.eq(teamName) : null;
    }

    BooleanExpression rightTeamNameEq(String teamName) {
        return hasText(teamName) ? league.rightTeam().name.eq(teamName) : null;
    }
}