package com.loljoa.server.web.dto.leagueSchedule;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class LeagueDto {
    private Long id;
    private String leagueName;
    private String weekNum;

    @JsonFormat(pattern = "YYYY-MM-dd HH:mm")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalDateTime startTime;

    @JsonFormat(pattern = "YYYY-MM-dd HH:mm")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalDateTime endTime;

    public LeagueDto(Long id, String leagueName, String weekNum, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = id;
        this.leagueName = leagueName;
        this.weekNum = weekNum;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}