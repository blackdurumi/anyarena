package com.blackdurumi.anyarena.post.service

import com.blackdurumi.anyarena.account.entity.Account
import com.blackdurumi.anyarena.account.service.AccountService
import com.blackdurumi.anyarena.post.dao.PostRepository
import com.blackdurumi.anyarena.post.dto.PostCreationRequest
import com.blackdurumi.anyarena.post.entity.Post
import spock.lang.Specification

class PostServiceTest extends Specification {
    PostService sut

    PostRepository postRepository = Mock()
    AccountService accountService = Mock()

    void setup() {
        sut = new PostService(
                postRepository,
                accountService,
        )
    }

    def postId = 1L
    def accountId = 2L
    def title = "title"
    def content = "content"
    def creationRequest = PostCreationRequest.builder()
            .title(title)
            .content(content)
            .posterId(accountId)
            .build()
    def account = Account.builder()
            .accountId(accountId)
            .build()
    def post = Post.builder()
            .postId(postId)
            .title(title)
            .content(content)
            .poster(account)
            .build()

    def "CreatePost"() {
        when:
        def result = sut.createPost(creationRequest)

        then:
        noExceptionThrown()
        1 * accountService.getById(accountId) >> account
        1 * postRepository.save(_) >> post
        result.getTitle() == title
        result.getContent() == content
    }

    def "GetPostContent"() {
        when:
        def result = sut.getPostContent(postId)

        then:
        noExceptionThrown()
        1 * postRepository.findById(postId) >> Optional.of(post)
        result.getTitle() == title
        result.getContent() == content
    }

    def "IncreaseViewCount"() {
        when:
        def result = sut.increaseViewCount(postId)

        then:
        noExceptionThrown()
        1 * postRepository.findById(postId) >> Optional.of(post)
        1 * postRepository.save(_) >> post
        result == 1L
    }

    def "IncreaseViewCount - count 100"() {
        given:
        def newPostId = 2L
        def newPost = Post.builder()
                .postId(newPostId)
                .title(title)
                .content(content)
                .poster(account)
                .views(100L)
                .build()

        when:
        def result = sut.increaseViewCount(newPostId)

        then:
        noExceptionThrown()
        1 * postRepository.findById(newPostId) >> Optional.of(newPost)
        1 * postRepository.save(_) >> newPost
        result == 101L
    }
}
