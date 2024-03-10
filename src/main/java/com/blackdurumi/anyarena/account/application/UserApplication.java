package com.blackdurumi.anyarena.account.application;

import com.blackdurumi.anyarena.account.dto.UserSignUpRequest;
import com.blackdurumi.anyarena.account.entity.User;
import com.blackdurumi.anyarena.account.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserApplication {

    private UserService userService;

    public User signUp(UserSignUpRequest request) {
        return userService.createUser(request);
    }
}
