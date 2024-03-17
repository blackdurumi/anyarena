package com.blackdurumi.anyarena.post.dto;

import com.blackdurumi.anyarena.post.entity.Post;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostDto {

    private final String title;
    private final String content;
    private final LocalDateTime createdAt;

    public static PostDto fromEntity(Post post) {
        return PostDto.builder()
            .title(post.getTitle())
            .content(post.getContent())
            .createdAt(post.getCreatedAt())
            .build();
    }
}
