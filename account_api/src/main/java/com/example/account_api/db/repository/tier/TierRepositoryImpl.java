package com.example.account_api.db.repository.tier;

import com.example.account_api.db.entity.Tier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.example.account_api.db.entity.QAccount.account;
import static com.example.account_api.db.entity.QTier.tier;

@Repository
@RequiredArgsConstructor
public class TierRepositoryImpl implements TierRepositoryCustom{
    private final JPAQueryFactory factory;

    @Override
    public Tier findByName(String name) {
        return factory
                .selectFrom(tier)
                .where(tier.name.eq(name))
                .fetchOne();
    }
}
