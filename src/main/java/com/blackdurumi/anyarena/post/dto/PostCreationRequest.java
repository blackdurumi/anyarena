package com.blackdurumi.anyarena.post.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostCreationRequest {

    private String title;
    private String content;
    private Long posterId;
}
