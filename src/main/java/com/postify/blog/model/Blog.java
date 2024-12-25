package com.postify.blog.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class Blog {

    @Id
    private String id;

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
