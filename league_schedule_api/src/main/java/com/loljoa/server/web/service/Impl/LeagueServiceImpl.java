package com.loljoa.server.web.service.Impl;

import com.loljoa.server.db.condition.LeagueSearchCondition;
import com.loljoa.server.db.entity.League;
import com.loljoa.server.db.repository.league.LeagueRepository;
import com.loljoa.server.web.dto.leagueSchedule.LeagueDto;
import com.loljoa.server.web.service.LeagueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LeagueServiceImpl implements LeagueService {
    private final LeagueRepository leagueRepository;

    @Override
    public LeagueDto getLeague(String name) {
        LeagueSearchCondition condition = new LeagueSearchCondition();
        condition.setLeagueName(name);
        League result = leagueRepository.getSingleLeagueSchedule(condition);
        return new LeagueDto(
                result.getLeagueId(),
                result.getLeagueName(),
                result.getWeekNum(),
                result.getStartTime(),
                result.getEndTime()
        );
    }
}
