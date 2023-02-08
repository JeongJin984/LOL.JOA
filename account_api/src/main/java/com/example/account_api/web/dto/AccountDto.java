package com.example.account_api.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class AccountDto {
    private Long accountId;
    private String username;
    private Long point;
    List<BettingData> bettingData = new ArrayList<>();

    @Data
    @NoArgsConstructor
    public static class BettingData {
        private String leftTeam;
        private String rightTeam;
        private String weekNum;

        @JsonFormat(pattern="yyyy-MM-dd HH:mm")
        private LocalDateTime startTime;

        private Long choiceId;
        private String choice;
        private Long choiceTotalPoint;
        private String odd;
        private Long point;

        public BettingData(
                String leftTeam,
                String rightTeam,
                String weekNum,
                LocalDateTime startTime,
                Long choiceId,
                String choice,
                Long choiceTotalPoint,
                String odd,
                Long point
        ) {
            this.leftTeam = leftTeam;
            this.rightTeam = rightTeam;
            this.weekNum = weekNum;
            this.startTime = startTime;
            this.choiceId = choiceId;
            this.choice = choice;
            this.choiceTotalPoint = choiceTotalPoint;
            this.odd = odd;
            this.point = point;
        }
    }
}
