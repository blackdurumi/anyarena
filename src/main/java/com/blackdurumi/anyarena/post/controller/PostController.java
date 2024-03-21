package com.blackdurumi.anyarena.post.controller;

import com.blackdurumi.anyarena.post.application.PostApplication;
import com.blackdurumi.anyarena.post.dto.PostCreationRequest;
import com.blackdurumi.anyarena.post.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostApplication postApplication;

    @PostMapping()
    public ResponseEntity<PostDto> createPost(@RequestBody PostCreationRequest request) {
        return ResponseEntity.ok(postApplication.createPost(request));
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> viewPost(@PathVariable("postId") Long postId) {
        return ResponseEntity.ok(postApplication.viewPost(postId));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable("postId") Long postId) {
        return ResponseEntity.ok(postApplication.deletePost(postId));
    }

    @PatchMapping("/{postId}")
    public ResponseEntity<PostDto> updatePost(
        @PathVariable("postId") Long postId, @RequestBody PostCreationRequest request) {
        return ResponseEntity.ok(postApplication.updatePost(postId, request));
    }
}
