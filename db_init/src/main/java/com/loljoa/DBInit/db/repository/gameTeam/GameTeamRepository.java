package com.loljoa.DBInit.db.repository.gameTeam;

import com.loljoa.DBInit.db.entity.BettingChoice;
import com.loljoa.DBInit.db.entity.GameTeam;
import com.loljoa.DBInit.db.repository.CommonRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameTeamRepository extends CommonRepository<GameTeam, Long>, GameTeamRepositoryCustom {
}
