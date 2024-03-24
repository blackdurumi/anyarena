package com.blackdurumi.anyarena.post.service;

import com.blackdurumi.anyarena.account.entity.Account;
import com.blackdurumi.anyarena.post.dao.PostRepository;
import com.blackdurumi.anyarena.post.dto.PostCreationRequest;
import com.blackdurumi.anyarena.post.dto.PostDto;
import com.blackdurumi.anyarena.post.dto.PostLikerDto;
import com.blackdurumi.anyarena.post.dto.PostLikersDto;
import com.blackdurumi.anyarena.post.entity.Post;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public PostDto createPost(PostCreationRequest request, Account poster) {
        Post post = Post.builder()
            .title(request.getTitle())
            .content(request.getContent())
            .poster(poster)
            .build();
        return PostDto.fromEntity(postRepository.save(post));
    }

    public PostDto getPostContent(Long postId) {
        Post post = getPost(postId);
        return PostDto.fromEntity(post);
    }

    private Post getPost(Long postId) {
        return postRepository.findById(postId)
            .orElseThrow(
                () -> new IllegalArgumentException("Post not found with postId: " + postId));
    }

    @Transactional
    public Long increaseViewCount(Long postId) {
        Post post = getPost(postId);
        post.setViews(post.getViews() + 1);
        return postRepository.save(post).getViews();
    }

    public String deletePost(Long postId) {
        postRepository.delete(getPost(postId));
        String successMessage = "success to delete post with postId: " + postId;
        log.info(successMessage);
        return successMessage;
    }

    public PostDto updatePost(Long postId, PostCreationRequest request) {
        Post post = getPost(postId);
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        return PostDto.fromEntity(postRepository.save(post));
    }

    public PostLikersDto getPostLikers(Long postId) {
        Post post = getPost(postId);
        return PostLikersDto.builder()
            .likers(getPostLikerEntities(postId).stream().map(PostLikerDto::fromEntity)
                .collect(Collectors.toList()))
            .build();
    }

    public List<Account> getPostLikerEntities(Long postId) {
        Post post = getPost(postId);
        return post.getLikers();
    }

    public String likePost(Account account, Long postId) {
        Post post = getPost(postId);
        post.getLikers().add(account);
        postRepository.save(post);
        return String.format("success to like post with postId: %d, accountId: %d",
            postId, account.getAccountId());
    }

    public String cancelLikePost(Account account, Long postId) {
        Post post = getPost(postId);
        post.getLikers().remove(account);
        postRepository.save(post);
        return String.format("success to cancel like post with postId: %d, accountId: %d",
            postId, account.getAccountId());
    }
}
