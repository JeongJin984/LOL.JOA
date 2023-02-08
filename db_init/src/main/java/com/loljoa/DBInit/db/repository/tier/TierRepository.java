package com.loljoa.DBInit.db.repository.tier;

import com.loljoa.DBInit.db.entity.Tier;
import com.loljoa.DBInit.db.repository.CommonRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TierRepository extends CommonRepository<Tier, Long>, TierRepositoryCustom {
}
