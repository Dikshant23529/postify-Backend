package com.postify.blog.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.postify.blog.model.UserProfile;

public interface UserProfileRepository extends MongoRepository<UserProfile, String> {

    Optional<UserProfile> findByRegisterUserId(String registerUserId);

}
