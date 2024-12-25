package com.postify.blog.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.postify.blog.model.Blog;

public interface BlogRepository extends MongoRepository<Blog, String> {

    List<Blog> findByTitle(String title);

    List<Blog> findByAuthor(String author);

    Optional<Blog> findByBlogId(String blogId);

    List<Blog> findByIsVisible(boolean isVisible);

}
