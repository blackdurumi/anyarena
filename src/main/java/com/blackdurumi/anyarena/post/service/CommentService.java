package com.blackdurumi.anyarena.post.service;

import com.blackdurumi.anyarena.account.entity.Account;
import com.blackdurumi.anyarena.post.dao.CommentRepository;
import com.blackdurumi.anyarena.post.dto.CommentDto;
import com.blackdurumi.anyarena.post.dto.CommentRequest;
import com.blackdurumi.anyarena.post.entity.Comment;
import com.blackdurumi.anyarena.post.entity.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentDto createComment(Post post, Account commenter, CommentRequest request) {
        Comment comment = Comment.builder()
            .post(post)
            .commenter(commenter)
            .content(request.getContent())
            .build();
        return CommentDto.fromEntity(commentRepository.save(comment));
    }
}
