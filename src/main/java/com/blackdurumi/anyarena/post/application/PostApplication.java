package com.blackdurumi.anyarena.post.application;

import com.blackdurumi.anyarena.account.entity.Account;
import com.blackdurumi.anyarena.account.service.AccountService;
import com.blackdurumi.anyarena.post.dto.PostCreationRequest;
import com.blackdurumi.anyarena.post.dto.PostDto;
import com.blackdurumi.anyarena.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class PostApplication {

    private final PostService postService;
    private final AccountService accountService;

    public PostDto createPost(PostCreationRequest request) {
        Account poster = accountService.getById(request.getPosterId());
        return postService.createPost(request, poster);
    }

    @Transactional
    public PostDto viewPost(Long postId) {
        postService.increaseViewCount(postId);
        return postService.getPostContent(postId);
    }

    public String deletePost(Long postId) {
        return postService.deletePost(postId);
    }
}
