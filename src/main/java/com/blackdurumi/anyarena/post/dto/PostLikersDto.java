package com.blackdurumi.anyarena.post.dto;

import com.blackdurumi.anyarena.account.entity.Account;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostLikersDto {

    private List<Account> likers;
}
