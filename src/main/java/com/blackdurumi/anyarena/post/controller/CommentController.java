package com.blackdurumi.anyarena.post.controller;

import com.blackdurumi.anyarena.post.application.CommentApplication;
import com.blackdurumi.anyarena.post.dto.CommentDto;
import com.blackdurumi.anyarena.post.dto.CommentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comments")
public class CommentController {

    private final CommentApplication commentApplication;

    @PostMapping()
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentRequest request) {
        return ResponseEntity.ok(commentApplication.createComment(request));
    }
}
