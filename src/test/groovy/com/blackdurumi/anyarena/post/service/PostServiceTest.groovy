package com.blackdurumi.anyarena.post.service

import com.blackdurumi.anyarena.account.entity.Account
import com.blackdurumi.anyarena.post.dao.PostRepository
import com.blackdurumi.anyarena.post.dto.PostCreationRequest
import com.blackdurumi.anyarena.post.entity.Post
import spock.lang.Specification

class PostServiceTest extends Specification {
    PostService sut

    PostRepository postRepository = Mock()

    void setup() {
        sut = new PostService(
                postRepository,
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
        def result = sut.createPost(creationRequest, account)

        then:
        noExceptionThrown()
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

    def "DeletePost"() {
        when:
        def result = sut.deletePost(postId)

        then:
        noExceptionThrown()
        1 * postRepository.findById(postId) >> Optional.of(post)
        1 * postRepository.delete(post)
        result == "success to delete post with postId: " + postId;
    }

    def "UpdatePost"() {
        given:
        def newTitle = "new title"
        def newContent = "new content"
        def newPost = Post.builder()
                .title(newTitle)
                .content(newContent)
                .build()
        def modificationRequest = PostCreationRequest.builder()
                .title(newTitle)
                .content(newContent)
                .posterId(accountId)
                .build()

        when:
        def result = sut.updatePost(postId, modificationRequest)

        then:
        noExceptionThrown()
        1 * postRepository.findById(postId) >> Optional.of(post)
        1 * postRepository.save(_) >> newPost
        result.getTitle() == newTitle
        result.getContent() == newContent
    }

    def "GetPostLikersDto"() {
        given:
        def likerId = 3L
        def liker = Account.builder()
                .accountId(likerId)
                .build()
        def likers = [liker]
        def postWithLikers = Post.builder()
                .postId(postId)
                .title(title)
                .content(content)
                .poster(account)
                .likers(likers)
                .build()

        when:
        def result = sut.getPostLikersDto(postId)

        then:
        noExceptionThrown()
        1 * postRepository.findById(postId) >> Optional.of(postWithLikers)
        result.getPostId() == postId
        result.getLikers().size() == 1
        result.getLikers().get(0).getAccountId() == likerId
    }

    def "GetPostLikers"() {
        given:
        def likerId = 3L
        def liker = Account.builder()
                .accountId(likerId)
                .build()
        def likers = [liker]
        def postWithLikers = Post.builder()
                .postId(postId)
                .title(title)
                .content(content)
                .poster(account)
                .likers(likers)
                .build()

        when:
        def result = sut.getPostLikers(postId)

        then:
        noExceptionThrown()
        1 * postRepository.findById(postId) >> Optional.of(postWithLikers)
        result.size() == 1
        result.get(0).getAccountId() == likerId
    }

    def "LikePost"() {
        given:
        def likerId = 3L
        def liker = Account.builder()
                .accountId(likerId)
                .build()
        def likers = [liker]
        def postWithLikers = Post.builder()
                .postId(postId)
                .title(title)
                .content(content)
                .poster(account)
                .likers(likers)
                .build()

        when:
        def result = sut.likePost(liker, postId)

        then:
        noExceptionThrown()
        1 * postRepository.findById(postId) >> Optional.of(postWithLikers)
        1 * postRepository.save(_) >> postWithLikers
        result == "success to like post with postId: " + postId + " by accountId: " + likerId
    }

    def "CancelLikePost"() {
        given:
        def likerId = 3L
        def liker = Account.builder()
                .accountId(likerId)
                .build()
        def likers = [liker]
        def postWithLikers = Post.builder()
                .postId(postId)
                .title(title)
                .content(content)
                .poster(account)
                .likers(likers)
                .build()

        when:
        def result = sut.cancelLikePost(liker, postId)

        then:
        noExceptionThrown()
        1 * postRepository.findById(postId) >> Optional.of(postWithLikers)
        1 * postRepository.save(_) >> postWithLikers
        result == "success to cancel like post with postId: " + postId + " by accountId: " + likerId
    }
}
