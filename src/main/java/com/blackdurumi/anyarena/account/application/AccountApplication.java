package com.blackdurumi.anyarena.account.application;

import com.blackdurumi.anyarena.account.dto.SignInRequest;
import com.blackdurumi.anyarena.account.dto.SignUpRequest;
import com.blackdurumi.anyarena.account.entity.Account;
import com.blackdurumi.anyarena.account.service.AccountService;
import com.blackdurumi.anyarena.common.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountApplication {

    private final AccountService accountService;
    private final SecurityUtil securityUtil;

    public Account signUp(SignUpRequest request) {
        return accountService.createAccount(request);
    }

    public Account signIn(SignInRequest request) {
        Account account = accountService.findByIdentity(request.getIdentity());

        if (!account.getEncryptedPassword().equals(securityUtil.encrypt(request.getPassword()))) {
            throw new RuntimeException("Password not matched");
        }

        return account;
    }

    public String deleteAccount(Long accountId) {
        return accountService.deleteAccount(accountId);
    }
}
