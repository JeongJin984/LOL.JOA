package com.loljoa.server.web.api;

import com.loljoa.server.web.dto.leagueSchedule.LeagueDto;
import com.loljoa.server.web.service.LeagueService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/league")
@RequiredArgsConstructor
public class LeagueApi {
    private final LeagueService leagueService;

    @GetMapping("/")
    LeagueDto getAllSchedule(
            @Nullable @RequestParam String name
    ) {
        return leagueService.getLeague(name);
    }
}
