package com.example.account_api.db.repository.account;

import com.example.account_api.db.entity.Account;
import com.example.account_api.db.repository.CommonRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CommonRepository<Account, Long>, AccountRepositoryCustom {
}
