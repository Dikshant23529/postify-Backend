package com.postify.blog.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.postify.blog.dto.blog.BlogDTO;
import com.postify.blog.services.impl.BlogServiceImpl;

@RestController
@RequestMapping("/com/postify/blog/v0/blog")
public class PrivateBlogControllers {

    @Autowired
    private BlogServiceImpl blogServiceImpl;

    @PostMapping("/create")
    public ResponseEntity<BlogDTO> createBlog(Principal principal, @RequestBody BlogDTO blogDTO) {
        return ResponseEntity.ok(blogServiceImpl.createBlog(principal, blogDTO));
    }

    @GetMapping("/all")
    public ResponseEntity<List<BlogDTO>> getAllBlogsByUser(Principal principal) {
        return ResponseEntity.ok(blogServiceImpl.getBlogByAuthor(principal));
    }

    @PutMapping("/update")
    public ResponseEntity<BlogDTO> updateBlog(Principal principal, @RequestBody BlogDTO blogDTO,
            @RequestParam String blogId) {
        return ResponseEntity.ok(blogServiceImpl.updateBlog(principal, blogDTO, blogId));
    }

    @GetMapping("/info")
    public ResponseEntity<BlogDTO> getBlogById(Principal principal, @RequestParam String blogId) {
        return ResponseEntity.ok(blogServiceImpl.getBlogById(principal, blogId));
    }

}
