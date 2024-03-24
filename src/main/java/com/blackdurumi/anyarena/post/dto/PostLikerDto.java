package com.blackdurumi.anyarena.post.dto;

import com.blackdurumi.anyarena.account.entity.Account;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostLikerDto {

    private Long accountId;
    private String name;

    public static PostLikerDto fromEntity(Account account) {
        return PostLikerDto.builder()
            .accountId(account.getAccountId())
            .name(account.getName())
            .build();
    }
}
