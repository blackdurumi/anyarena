package com.blackdurumi.anyarena.post.application

import com.blackdurumi.anyarena.post.dto.PostCreationRequest
import com.blackdurumi.anyarena.post.dto.PostDto
import com.blackdurumi.anyarena.post.service.PostService
import spock.lang.Specification

class PostApplicationTest extends Specification {
    PostApplication sut

    PostService postService = Mock()

    void setup() {
        sut = new PostApplication(
                postService,
        )
    }

    def postId = 1L
    def title = "title"
    def content = "content"
    def creationRequest = PostCreationRequest.builder()
            .title(title)
            .content(content)
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
        1 * postService.createPost(creationRequest) >> postDto
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
}
