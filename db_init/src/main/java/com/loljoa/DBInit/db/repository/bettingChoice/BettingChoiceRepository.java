package com.loljoa.DBInit.db.repository.bettingChoice;

import com.loljoa.DBInit.db.entity.BettingChoice;
import com.loljoa.DBInit.db.repository.CommonRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BettingChoiceRepository extends CommonRepository<BettingChoice, Long>, BettingChoiceRepositoryCustom {
}
