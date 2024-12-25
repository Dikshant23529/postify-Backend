package com.postify.blog.utils.model.mapper;

import com.postify.blog.dto.blog.BlogDTO;
import com.postify.blog.dto.user.UserRequestDto;
import com.postify.blog.dto.user.UserResponseDto;
import com.postify.blog.dto.user.profile.UserProfileRequestDto;
import com.postify.blog.dto.user.profile.UserProfileResponseDTO;
import com.postify.blog.model.Blog;
import com.postify.blog.model.RegisterUser;
import com.postify.blog.model.UserProfile;

public class UserModelMapper {

    public static UserResponseDto mapToUserResponseDto(RegisterUser registerUser) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setUserId(registerUser.getUserId());
        userResponseDto.setUsername(registerUser.getUsername());
        userResponseDto.setPassword(registerUser.getPassword());
        userResponseDto.setRole(registerUser.getRole());
        return userResponseDto;
    }

    public static RegisterUser mapToRegisterUser(UserRequestDto userRequestDto) {
        RegisterUser registerUser = new RegisterUser();
        registerUser.setUsername(userRequestDto.getUsername());
        registerUser.setPassword(userRequestDto.getPassword());
        registerUser.setRole("ROLE_USER");
        return registerUser;
    }

    public static UserProfileResponseDTO mapToUserProfileResponseDto(UserProfile userProfile) {
        UserProfileResponseDTO userProfileResponseDTO = new UserProfileResponseDTO();
        userProfileResponseDTO.setUserProfileId(userProfile.getUserProfileId());
        userProfileResponseDTO.setName(userProfile.getName());
        userProfileResponseDTO.setMiddleName(userProfile.getMiddleName());
        userProfileResponseDTO.setLastName(userProfile.getLastName());
        userProfileResponseDTO.setAddress(userProfile.getAddress());
        userProfileResponseDTO.setGitHubProfile(userProfile.getGitHubProfile());
        userProfileResponseDTO.setLinkedinProfile(userProfile.getLinkedinProfile());
        userProfileResponseDTO.setOtherLinks(userProfile.getOtherLinks());
        userProfileResponseDTO.setPortFolioLink(userProfile.getPortFolioLink());
        userProfileResponseDTO.setPhoneNo(userProfile.getPhoneNo());
        userProfileResponseDTO.setDateOfBirth(userProfile.getDateOfBirth());
        userProfileResponseDTO.setProfileImage(userProfile.getProfileImage());
        userProfileResponseDTO.setExperience(userProfile.getExperience());
        userProfileResponseDTO.setEducation(userProfile.getEducation());
        userProfileResponseDTO.setCertificates(userProfile.getCertificate());

        return userProfileResponseDTO;

    }

    public static UserProfile mapToUserProfile(UserProfileRequestDto userProfileRequestDto) {
        UserProfile userProfile = new UserProfile();
        userProfile.setUserProfileId(userProfileRequestDto.getUserProfileId());
        userProfile.setName(userProfileRequestDto.getName());
        userProfile.setMiddleName(userProfileRequestDto.getMiddleName());
        userProfile.setLastName(userProfileRequestDto.getLastName());
        userProfile.setAddress(userProfileRequestDto.getAddress());
        userProfile.setGitHubProfile(userProfileRequestDto.getGitHubProfile());
        userProfile.setLinkedinProfile(userProfileRequestDto.getLinkedinProfile());
        userProfile.setOtherLinks(userProfileRequestDto.getOtherLinks());
        userProfile.setPortFolioLink(userProfileRequestDto.getPortFolioLink());
        userProfile.setPhoneNo(userProfileRequestDto.getPhoneNo());
        userProfile.setDateOfBirth(userProfileRequestDto.getDateOfBirth());
        userProfile.setProfileImage(userProfileRequestDto.getProfileImage());
        userProfile.setExperience(userProfileRequestDto.getExperience());
        userProfile.setEducation(userProfileRequestDto.getEducation());
        userProfile.setCertificate(userProfileRequestDto.getCertificate());
        userProfile.setRegisterUserId(userProfileRequestDto.getRegisterUserId());

        return userProfile;
    }

    public static BlogDTO mapToBlogDTO(Blog blog) {

        BlogDTO blogDTO = new BlogDTO();

        blogDTO.setBlogId(blog.getBlogId());
        blogDTO.setAuthor(blog.getAuthor());
        blogDTO.setBody(blog.getBody());
        blogDTO.setCreatedAt(blog.getCreatedAt());
        blogDTO.setDescription(blog.getDescription());
        blogDTO.setDocs(blog.getDocs());
        blogDTO.setImages(blog.getImages());
        blogDTO.setTitle(blogDTO.getTitle());
        blogDTO.setUpdateAt(blog.getUpdateAt());
        blogDTO.setVideos(blog.getVideos());

        return blogDTO;

    }

    public static Blog mapToBlog(BlogDTO blogDTO) {

        Blog blog = new Blog();

        blog.setBlogId(blogDTO.getBlogId());
        blog.setAuthor(blogDTO.getAuthor());
        blog.setBody(blogDTO.getBody());
        blog.setCreatedAt(blogDTO.getCreatedAt());
        blog.setDescription(blogDTO.getDescription());
        blog.setDocs(blogDTO.getDocs());
        blog.setImages(blogDTO.getImages());
        blog.setTitle(blogDTO.getTitle());
        blog.setUpdateAt(blogDTO.getUpdateAt());
        blog.setVideos(blogDTO.getVideos());

        return blog;

    }

}
