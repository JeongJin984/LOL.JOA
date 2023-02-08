package com.loljoa.server.web.service;

import com.loljoa.server.web.dto.leagueSchedule.ScheduleDto;

public interface LeagueScheduleService {
    ScheduleDto getSchedule(String category, String WeekNum);
}
