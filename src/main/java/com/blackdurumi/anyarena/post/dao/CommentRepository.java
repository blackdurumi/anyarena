package com.blackdurumi.anyarena.post.dao;

import com.blackdurumi.anyarena.post.entity.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {

}
