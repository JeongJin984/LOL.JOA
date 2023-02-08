package com.example.account_api.db.repository.tier;

import com.example.account_api.db.entity.Tier;
import com.example.account_api.db.repository.CommonRepository;

public interface TierRepository extends CommonRepository<Tier, Long>, TierRepositoryCustom {
}
