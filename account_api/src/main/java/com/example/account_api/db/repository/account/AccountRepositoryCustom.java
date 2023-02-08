package com.example.account_api.db.repository.account;

import com.example.account_api.db.entity.Account;

public interface AccountRepositoryCustom {
    Account getUserByCredential(String username, String password);
}
