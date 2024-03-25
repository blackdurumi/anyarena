package com.blackdurumi.anyarena.post.application

import com.blackdurumi.anyarena.account.entity.Account
import com.blackdurumi.anyarena.account.service.AccountService
import com.blackdurumi.anyarena.post.dto.PostCreationRequest
import com.blackdurumi.anyarena.post.dto.PostDto
import com.blackdurumi.anyarena.post.dto.PostLikerDto
import com.blackdurumi.anyarena.post.dto.PostLikersDto
import com.blackdurumi.anyarena.post.service.PostService
import spock.lang.Specification

class PostApplicationTest extends Specification {
    PostApplication sut

    PostService postService = Mock()
    AccountService accountService = Mock()

    void setup() {
        sut = new PostApplication(
                postService,
                accountService,
        )
    }

    def postId = 1L
    def title = "title"
    def content = "content"
    def posterId = 2L
    def creationRequest = PostCreationRequest.builder()
            .title(title)
            .content(content)
            .posterId(posterId)
            .build()
    def poster = Account.builder()
            .accountId(posterId)
            .build()
    def postDto = PostDto.builder()
            .title(title)
            .content(content)
            .build()

    def "CreatePost"() {
        when:
        def result = sut.createPost(creationRequest)

        then:
        noExceptionThrown()
        1 * accountService.getById(posterId) >> poster
        1 * postService.createPost(creationRequest, poster) >> postDto
        result.getTitle() == title
        result.getContent() == content
    }

    def "ViewPost"() {
        when:
        def result = sut.viewPost(postId)

        then:
        noExceptionThrown()
        1 * postService.increaseViewCount(postId)
        1 * postService.getPostContent(postId) >> postDto
        result.getTitle() == title
        result.getContent() == content
    }

    def "UpdatePost"() {
        given:
        sut.createPost(creationRequest)
        def newTitle = "new title"
        def newContent = "new content"
        def newPostDto = PostDto.builder()
                .title(newTitle)
                .content(newContent)
                .build()
        def modificationRequest = PostCreationRequest.builder()
                .title(newTitle)
                .content(newContent)
                .posterId(posterId)
                .build()

        when:
        def result = sut.updatePost(postId, modificationRequest)

        then:
        noExceptionThrown()
        1 * postService.updatePost(postId, modificationRequest) >> newPostDto
        result.getTitle() == newTitle
        result.getContent() == newContent
    }

    def "GetPostLikers"() {
        given:
        def likerId = 3L
        def likerName = "liker"
        def likerDto = PostLikerDto.builder()
                .accountId(likerId)
                .name(likerName)
                .build()
        def postLikersDto = PostLikersDto.builder()
                .likers(Arrays.asList(likerDto))
                .build()

        when:
        def result = sut.getPostLikers(postId)

        then:
        noExceptionThrown()
        1 * postService.getPostLikersDto(postId) >> postLikersDto
        result.getLikers().size() == 1
        result.getLikers().get(0).getAccountId() == likerId
        result.getLikers().get(0).getName() == likerName
    }

    def "LikeOrCancelPost - like"() {
        given:
        def likerId = 3L
        def liker = Account.builder()
                .accountId(likerId)
                .build()

        when:
        def result = sut.likeOrCancelPost(likerId, postId)

        then:
        noExceptionThrown()
        1 * postService.getPostLikers(postId) >> Arrays.asList()
        1 * accountService.getById(likerId) >> liker
        0 * postService.cancelLikePost(liker, postId) >> "cancel"
        1 * postService.likePost(liker, postId) >> "success"
        result == "success"
    }

    def "LikeOrCancelPost - cancel"() {
        given:
        def likerId = 3L
        def liker = Account.builder()
                .accountId(likerId)
                .build()

        when:
        def result = sut.likeOrCancelPost(likerId, postId)

        then:
        noExceptionThrown()
        1 * postService.getPostLikers(postId) >> Arrays.asList(liker)
        1 * accountService.getById(likerId) >> liker
        1 * postService.cancelLikePost(liker, postId) >> "cancel"
        0 * postService.likePost(liker, postService) >> "success"
        result == "cancel"
    }
}
