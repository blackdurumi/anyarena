package com.blackdurumi.anyarena.account.service;

import com.blackdurumi.anyarena.account.dao.UserRepository;
import com.blackdurumi.anyarena.account.dto.UserSignUpRequest;
import com.blackdurumi.anyarena.account.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public User createUser(UserSignUpRequest request) {
        return userRepository.save(
            User.builder()
                .identity(request.getIdentity())
                .password(request.getPassword())
                .name(request.getName())
                .phoneNumber(request.getPhoneNumber())
                .build()
        );
    }
}
