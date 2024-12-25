package com.postify.blog.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Document
public class UserProfile {

    @Id
    private String id;

    @NotBlank
    @NotEmpty
    @NotNull
    private String userProfileId;

    @NotBlank
    @NotEmpty
    @NotNull
    private String name;

    private String middleName;

    private String lastName;

    private String address;

    private String gitHubProfile;

    private String linkedinProfile;

    private List<String> otherLinks;

    private String portFolioLink;

    private String phoneNo;

    private String dateOfBirth;

    private String profileImage;

    private String registerUserId;

    private List<Experience> experience;

    private List<Education> education;

    private List<Certificate> certificate;

}
