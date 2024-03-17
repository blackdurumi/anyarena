package com.blackdurumi.anyarena.post.application;

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

    public PostDto createPost(PostCreationRequest request) {
        return postService.createPost(request);
    }

    @Transactional
    public PostDto viewPost(Long postId) {
        postService.increaseViewCount(postId);
        return postService.getPostContent(postId);
    }
}
