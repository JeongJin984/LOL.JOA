package com.example.account_api.db.repository.tier;

import com.example.account_api.db.entity.Tier;

public interface TierRepositoryCustom {
    public Tier findByName(String name);
}
