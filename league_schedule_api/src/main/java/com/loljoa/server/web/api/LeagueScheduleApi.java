package com.loljoa.server.web.api;

import com.loljoa.server.web.dto.leagueSchedule.ScheduleDto;
import com.loljoa.server.web.service.LeagueScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/league")
@RequiredArgsConstructor
public class LeagueScheduleApi {
    private final LeagueScheduleService leagueScheduleService;

    @GetMapping("/schedule/all")
    ScheduleDto getAllSchedule(
            @Nullable @RequestParam String category,
            @Nullable @RequestParam String weekNum
    ) {
        return leagueScheduleService.getSchedule(category, weekNum);
    }

}
