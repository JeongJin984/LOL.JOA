package com.loljoa.server.web.service;

import com.loljoa.server.web.dto.leagueSchedule.LeagueDto;

public interface LeagueService {
    public LeagueDto getLeague(String name);
}
