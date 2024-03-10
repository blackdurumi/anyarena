package com.blackdurumi.anyarena.account.controller;

import com.blackdurumi.anyarena.account.application.AccountApplication;
import com.blackdurumi.anyarena.account.dto.SignUpRequest;
import com.blackdurumi.anyarena.account.entity.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class AccountController {

    @Autowired
    private AccountApplication accountApplication;

    @PostMapping("/sign-up")
    public ResponseEntity<Account> signUp(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(accountApplication.signUp(request));
    }
}
