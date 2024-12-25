package com.postify.blog.model;


import lombok.Data;

@Data
public class Certificate {

    private String id;

    private String certificateId;

    private String certificateName;

    private String issuedAt;

    private String validTill;

    private String certificateUrl;

    private String experienceId;
}
