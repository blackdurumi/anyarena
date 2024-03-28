package com.blackdurumi.anyarena.post.application;

import com.blackdurumi.anyarena.account.entity.Account;
import com.blackdurumi.anyarena.account.service.AccountService;
import com.blackdurumi.anyarena.post.dto.CommentDto;
import com.blackdurumi.anyarena.post.dto.CommentRequest;
import com.blackdurumi.anyarena.post.entity.Post;
import com.blackdurumi.anyarena.post.service.CommentService;
import com.blackdurumi.anyarena.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentApplication {

    private final CommentService commentService;
    private final PostService postService;
    private final AccountService accountService;

    public CommentDto createComment(CommentRequest request) {
        Post post = postService.getPost(request.getPostId());
        Account commenter = accountService.getById(request.getCommenterId());
        return commentService.createComment(post, commenter, request);
    }
}
