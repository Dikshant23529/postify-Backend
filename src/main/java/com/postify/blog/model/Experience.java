package com.postify.blog.model;

import java.util.Set;



import lombok.Data;

@Data
public class Experience {

    private String expId;

    private String organizationName;

    private String jobTitle;

    private String designation;

    private int yearOfExperience;

    private String awards;

    private Set<String> project;

    private String about;

    private String roleDescription;

    private String startDate;

    private boolean isCurrent;

    private String endDate;

}
