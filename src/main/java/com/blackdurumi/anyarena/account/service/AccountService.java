package com.blackdurumi.anyarena.account.service;

import com.blackdurumi.anyarena.account.dao.AccountRepository;
import com.blackdurumi.anyarena.account.dto.SignUpRequest;
import com.blackdurumi.anyarena.account.entity.Account;
import com.blackdurumi.anyarena.common.SecurityUtil;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    private final SecurityUtil securityUtil;

    @Transactional
    public Account createAccount(SignUpRequest request) {
        String encryptedPassword = securityUtil.encrypt(request.getPassword());
        return accountRepository.save(
            Account.builder()
                .identity(request.getIdentity())
                .encryptedPassword(encryptedPassword)
                .name(request.getName())
                .phoneNumber(request.getPhoneNumber())
                .build()
        );
    }

    public Account findByIdentity(String identity) {
        return accountRepository.findByIdentity(identity)
            .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    public Account getById(Long accountId) {
        return accountRepository.findById(accountId)
            .orElseThrow(() -> new RuntimeException("Account not found"));
    }
}
