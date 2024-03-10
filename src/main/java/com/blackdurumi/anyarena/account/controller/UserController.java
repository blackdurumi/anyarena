package com.blackdurumi.anyarena.account.controller;

import com.blackdurumi.anyarena.account.application.UserApplication;
import com.blackdurumi.anyarena.account.dto.UserSignUpRequest;
import com.blackdurumi.anyarena.account.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private UserApplication userApplication;

    @PostMapping("/sign-up")
    public ResponseEntity<User> signUp(@RequestBody UserSignUpRequest request) {
        return ResponseEntity.ok(userApplication.signUp(request));
    }
}
