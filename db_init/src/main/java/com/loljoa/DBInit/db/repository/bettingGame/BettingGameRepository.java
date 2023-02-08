package com.loljoa.DBInit.db.repository.bettingGame;

import com.loljoa.DBInit.db.entity.BettingGame;
import com.loljoa.DBInit.db.repository.CommonRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BettingGameRepository extends CommonRepository<BettingGame, Long>, BettingGameRepositoryCustom {
}
