package com.blackdurumi.anyarena.post.dto;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostLikersDto {

    private Long postId;
    private List<PostLikerDto> likers;
}
