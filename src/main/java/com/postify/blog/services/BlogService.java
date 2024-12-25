package com.postify.blog.services;

import java.security.Principal;
import java.util.List;

import com.postify.blog.dto.blog.BlogDTO;

public interface BlogService {

    BlogDTO createBlog(Principal principal, BlogDTO blogDTO);

    List<BlogDTO> getBlogByAuthor(Principal principal);

    BlogDTO updateBlog(Principal principal, BlogDTO blogDTO, String blogId);

    boolean deleteBlog(Principal principal);

    BlogDTO getBlogById(Principal principal, String blogId);

    List<BlogDTO> getPublicBlogs();

    BlogDTO getPublicBlogById(String blogId);

}
