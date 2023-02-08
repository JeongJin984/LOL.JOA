package com.example.account_api.web.api;

import com.example.account_api.web.dto.AccountDto;
import com.example.account_api.web.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountAPI {
    private final AccountService accountService;

    @PutMapping("/signup")
    void signUp(
            @RequestParam String username,
            @RequestParam String password

    ) {
        accountService.signUp(username, password);
    }

    @GetMapping("/login")
    AccountDto login(
            @RequestParam String username,
            @RequestParam String password,
            HttpServletResponse response
    ) {
        Cookie suid = new Cookie("SUID", username);
        suid.setHttpOnly(true);
        suid.setPath("/");
        response.addCookie(suid);
        return accountService.login(username, password);
    }
}
