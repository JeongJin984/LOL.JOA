package com.example.account_api.db.repository.account;

import com.example.account_api.db.entity.Account;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.example.account_api.db.entity.QAccount.account;

@Repository
@RequiredArgsConstructor
public class AccountRepositoryImpl implements AccountRepositoryCustom {
    private final JPAQueryFactory factory;

    @Override
    public Account getUserByCredential(String username, String password){
        return factory
                .selectFrom(account)
                .where(account.username.eq(username).and(account.password.eq(password)))
                .fetchOne();
    }
}
