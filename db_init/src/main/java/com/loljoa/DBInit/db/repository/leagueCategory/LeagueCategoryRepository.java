package com.loljoa.DBInit.db.repository.leagueCategory;

import com.loljoa.DBInit.db.entity.BettingChoice;
import com.loljoa.DBInit.db.entity.LeagueCategory;
import com.loljoa.DBInit.db.repository.CommonRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeagueCategoryRepository extends CommonRepository<LeagueCategory, Long>, LeagueCategoryRepositoryCustom {
}
