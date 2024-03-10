package com.blackdurumi.anyarena.account.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserSignUpRequest {

    private String identity;
    private String password;
    private String name;
    private String phoneNumber;
}
