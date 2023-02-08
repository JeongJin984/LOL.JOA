package com.example.account_api.web.service;

import com.example.account_api.db.entity.Account;
import com.example.account_api.db.entity.Tier;
import com.example.account_api.db.repository.account.AccountRepository;
import com.example.account_api.db.repository.tier.TierRepository;
import com.example.account_api.web.dto.AccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final TierRepository tierRepository;
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${back.url.betting-game-api}")
    private String bettingGameAPIURL;

    public void signUp(String username, String password) {
        Tier bronze = tierRepository.findByName("bronze");
        accountRepository.save(new Account(username, password, bronze, 1000L));
    }

    public AccountDto login(String username, String password) {
        Account account = accountRepository.getUserByCredential(username, password);
        if(account != null) {
            return restTemplate.getForObject(bettingGameAPIURL + "/api/betting/game/user?username=" + account.getUsername(), AccountDto.class);
        } else {
             return null;
        }
    }
}
