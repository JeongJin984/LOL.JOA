package com.loljoa.server.web.dto.leagueSchedule;

import com.loljoa.server.db.entity.League;
import lombok.Data;


import java.util.ArrayList;
import java.util.List;

@Data
public class ScheduleDto {
    String category;
    List<LeagueDto> schedules = new ArrayList<>();

    public void setSchedules(List<League> schedules) {
        for(League v : schedules) {
            this.schedules.add(
                    new LeagueDto(v.getLeagueId(), v.getLeagueName(), v.getWeekNum(), v.getStartTime(), v.getEndTime())
            );
        }
    }
}

