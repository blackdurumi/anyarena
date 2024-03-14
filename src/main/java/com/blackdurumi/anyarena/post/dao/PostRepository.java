package com.blackdurumi.anyarena.post.dao;

import com.blackdurumi.anyarena.post.entity.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {

}
