package com.postify.blog.dto.user.profile;

import java.util.List;

import com.postify.blog.model.Certificate;
import com.postify.blog.model.Education;
import com.postify.blog.model.Experience;

import lombok.Data;

@Data
public class UserProfileResponseDTO {

    private String userProfileId;

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

    private List<Experience> experience;

    private List<Education> education;

    private List<Certificate> certificates;

}
