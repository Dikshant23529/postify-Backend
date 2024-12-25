package com.postify.blog.model;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;

@Data
public class Videos {

    
    private String id = UUID.randomUUID().toString();
    private String videoURL;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

}
