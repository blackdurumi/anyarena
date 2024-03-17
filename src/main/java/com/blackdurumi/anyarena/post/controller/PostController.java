package com.blackdurumi.anyarena.post.controller;

import com.blackdurumi.anyarena.post.application.PostApplication;
import com.blackdurumi.anyarena.post.dto.PostCreationRequest;
import com.blackdurumi.anyarena.post.dto.PostDto;
import javax.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostApplication postApplication;

    @PostMapping()
    public ResponseEntity<PostDto> createPost(PostCreationRequest request) {
        return ResponseEntity.ok(postApplication.createPost(request));
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> viewPost(@PathParam("postId") Long postId) {
        return ResponseEntity.ok(postApplication.viewPost(postId));
    }
}
