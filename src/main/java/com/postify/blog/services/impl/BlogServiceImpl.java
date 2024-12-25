package com.postify.blog.services.impl;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.postify.blog.dto.blog.BlogDTO;
import com.postify.blog.exceptions.ResourceNotFoundException;
import com.postify.blog.model.Blog;
import com.postify.blog.model.RegisterUser;
import com.postify.blog.repositories.BlogRepository;
import com.postify.blog.repositories.UserRegistrationRepository;
import com.postify.blog.services.BlogService;
import com.postify.blog.utils.model.mapper.UserModelMapper;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UserRegistrationRepository userRegistrationRepository;

    @Override
    public BlogDTO createBlog(Principal principal, BlogDTO blogDTO) {

        RegisterUser registerUser = userRegistrationRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        blogDTO.setAuthor(registerUser.getUsername());
        blogDTO.setBlogId(UUID.randomUUID().toString());
        blogDTO.setCreatedAt(LocalDateTime.now());
        blogDTO.setUpdateAt(LocalDateTime.now());
        blogDTO.setVisible(false);

        return UserModelMapper.mapToBlogDTO(blogRepository.save(UserModelMapper.mapToBlog(blogDTO)));

    }

    @Override
    public List<BlogDTO> getBlogByAuthor(Principal principal) {
        RegisterUser registerUser = userRegistrationRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Blog> blogsList = blogRepository.findByAuthor(registerUser.getUsername());

        return blogsList.stream().map((blog) -> UserModelMapper.mapToBlogDTO(blog)).collect(Collectors.toList());
    }

    @Override
    public BlogDTO updateBlog(Principal principal, BlogDTO blogDTO, String blogId) {

        String username = principal.getName();

        Blog blogById = blogRepository.findByBlogId(blogId)
                .orElseThrow(() -> new ResourceNotFoundException("No Blog found with the Given: " + blogId));

        blogById.setBody(blogDTO.getBody());
        blogById.setAuthor(username);
        blogById.setDescription(blogDTO.getDescription());
        blogById.setVisible(blogDTO.isVisible());
        blogById.setUpdateAt(blogDTO.getUpdateAt());
        Blog blog = blogRepository.save(blogById);

        return UserModelMapper.mapToBlogDTO(blog);

    }

    @Override
    public boolean deleteBlog(Principal principal) {
        return false;
    }

    @Override
    public BlogDTO getBlogById(Principal principal, String blogId) {
        principal.getName();
        Blog blogById = blogRepository.findByBlogId(blogId)
                .orElseThrow(() -> new ResourceNotFoundException("No Blog found with the Given: " + blogId));
        return UserModelMapper.mapToBlogDTO(blogById);
    }

    @Override
    public List<BlogDTO> getPublicBlogs() {
        return blogRepository.findByIsVisible(true).stream().map((blog) -> UserModelMapper.mapToBlogDTO(blog))
                .collect(Collectors.toList());
    }

    @Override
    public BlogDTO getPublicBlogById(String blogId) {
        Blog blogById = blogRepository.findByIsVisible(true).stream().filter((blog) -> blog.getBlogId() == blogId)
                .findFirst().orElseThrow(() -> new ResourceNotFoundException("No Blog Found"));
        return UserModelMapper.mapToBlogDTO(blogById);
    }

}
