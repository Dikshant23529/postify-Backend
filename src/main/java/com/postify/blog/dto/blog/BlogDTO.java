package com.postify.blog.dto.blog;

import java.time.LocalDateTime;
import java.util.List;

import com.postify.blog.model.Docs;
import com.postify.blog.model.Images;
import com.postify.blog.model.Videos;

import lombok.Data;

@Data
public class BlogDTO {

    private String blogId;

    private String author;

    private String title;

    private String description;

    private String body;

    private List<Images> images;

    private List<Docs> docs;

    private List<Videos> videos;

    private LocalDateTime createdAt;

    private LocalDateTime updateAt;

    private boolean isVisible;

}
