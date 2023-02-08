package com.loljoa.DBInit.db.repository.bettingState;

import com.loljoa.DBInit.db.entity.BettingChoice;
import com.loljoa.DBInit.db.entity.BettingState;
import com.loljoa.DBInit.db.repository.CommonRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BettingStateRepository extends CommonRepository<BettingState, Long>, BettingStateRepositoryCustom {
}
