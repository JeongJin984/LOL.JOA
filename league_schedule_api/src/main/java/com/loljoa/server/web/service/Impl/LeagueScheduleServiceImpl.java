package com.loljoa.server.web.service.Impl;

import com.loljoa.server.db.condition.LeagueSearchCondition;
import com.loljoa.server.db.entity.League;
import com.loljoa.server.db.repository.league.LeagueRepository;
import com.loljoa.server.web.dto.leagueSchedule.ScheduleDto;
import com.loljoa.server.web.service.LeagueScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeagueScheduleServiceImpl implements LeagueScheduleService {
    private final LeagueRepository leagueRepository;

    @Override
    public ScheduleDto getSchedule(String category, String weekNum) {
        LeagueSearchCondition condition = new LeagueSearchCondition();
        condition.setCategoryName(category);
        condition.setWeekNum(weekNum);

        List<League> leagueSchedule = leagueRepository.getLeagueScheduleList(condition);
        ScheduleDto result = new ScheduleDto();
        result.setCategory(category);
        result.setSchedules(leagueSchedule);
        return result;
    }
}
