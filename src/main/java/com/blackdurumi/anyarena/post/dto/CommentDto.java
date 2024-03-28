package com.blackdurumi.anyarena.post.dto;

import com.blackdurumi.anyarena.post.entity.Comment;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentDto {

    private Long postId;
    private Long commenterId;
    private String content;
    private LocalDateTime createdAt;

    public static CommentDto fromEntity(Comment comment) {
        return CommentDto.builder()
            .postId(comment.getPost().getPostId())
            .commenterId(comment.getCommenter().getAccountId())
            .content(comment.getContent())
            .createdAt(comment.getCreatedAt())
            .build();
    }
}
