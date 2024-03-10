package com.blackdurumi.anyarena.account.application;

import com.blackdurumi.anyarena.account.dto.SignUpRequest;
import com.blackdurumi.anyarena.account.entity.Account;
import com.blackdurumi.anyarena.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountApplication {

    @Autowired
    private AccountService accountService;

    public Account signUp(SignUpRequest request) {
        return accountService.createAccount(request);
    }
}
