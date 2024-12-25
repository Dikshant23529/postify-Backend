package com.postify.blog.model;

import java.util.UUID;

import lombok.Data;


@Data
public class Images {
    
    private String imageId = UUID.randomUUID().toString();

    private String imageUrl;
    
}
