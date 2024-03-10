package com.blackdurumi.anyarena.account.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {

    private String identity;
    private String password;
    private String name;
    private String phoneNumber;
}
