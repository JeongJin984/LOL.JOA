package com.loljoa.DBInit.db.repository.account;

import com.loljoa.DBInit.db.entity.Account;
import com.loljoa.DBInit.db.repository.CommonRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends AccountRepositoryCustom, CommonRepository<Account, Long> {
}
