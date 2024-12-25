package com.postify.blog.model;

import java.util.UUID;

import lombok.Data;

@Data
public class Docs {

    private String id = UUID.randomUUID().toString();

    private String docURL;

}
