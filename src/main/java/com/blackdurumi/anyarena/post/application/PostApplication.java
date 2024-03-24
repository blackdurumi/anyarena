package com.blackdurumi.anyarena.post.application;

import com.blackdurumi.anyarena.account.entity.Account;
import com.blackdurumi.anyarena.account.service.AccountService;
import com.blackdurumi.anyarena.post.dto.PostCreationRequest;
import com.blackdurumi.anyarena.post.dto.PostDto;
import com.blackdurumi.anyarena.post.dto.PostLikersDto;
import com.blackdurumi.anyarena.post.service.PostService;
import java.util.List;
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

    public PostDto updatePost(Long postId, PostCreationRequest request) {
        return postService.updatePost(postId, request);
    }

    public PostLikersDto getPostLikers(Long postId) {
        return postService.getPostLikersDto(postId);
    }

    @Transactional
    public String likeOrCancelPost(Long userId, Long postId) {
        List<Account> likers = postService.getPostLikers(postId);
        Account user = accountService.getById(userId);

        if (likers.contains(user)) {
            return postService.cancelLikePost(user, postId);
        }
        return postService.likePost(user, postId);
    }
}
