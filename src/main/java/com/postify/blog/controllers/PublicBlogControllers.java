package com.postify.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.postify.blog.dto.blog.BlogDTO;
import com.postify.blog.services.impl.BlogServiceImpl;

@RequestMapping("/api/blog/public")
public class PublicBlogControllers {

    @Autowired
    private BlogServiceImpl blogServiceImpl;

    @GetMapping("/all")
    public ResponseEntity<List<BlogDTO>> getAllPublicBlogs() {
        return ResponseEntity.ok(blogServiceImpl.getPublicBlogs());
    }

    @GetMapping("/info")
    public ResponseEntity<BlogDTO> getPublicBlogById(@RequestParam String blogId) {
        return ResponseEntity.ok(blogServiceImpl.getPublicBlogById(blogId));
    }

}
