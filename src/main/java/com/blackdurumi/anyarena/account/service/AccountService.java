package com.blackdurumi.anyarena.account.service;

import com.blackdurumi.anyarena.account.dao.AccountRepository;
import com.blackdurumi.anyarena.account.dto.SignUpRequest;
import com.blackdurumi.anyarena.account.entity.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account createAccount(SignUpRequest request) {
        return accountRepository.save(
            Account.builder()
                .identity(request.getIdentity())
                .password(request.getPassword())
                .name(request.getName())
                .phoneNumber(request.getPhoneNumber())
                .build()
        );
    }
}
