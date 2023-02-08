package com.loljoa.server.db.condition;

import lombok.Data;

@Data
public class LeagueSearchCondition {
    private String categoryName;
    private String weekNum;
    private String leftTeamName;
    private String rightTeamName;
    private String leagueName;
}
