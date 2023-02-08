package com.loljoa.DBInit.db.repository.league;

import com.loljoa.DBInit.db.entity.BettingChoice;
import com.loljoa.DBInit.db.entity.League;
import com.loljoa.DBInit.db.repository.CommonRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeagueRepository extends CommonRepository<League, Long>, LeagueRepositoryCustom {
}
