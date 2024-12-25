package com.postify.blog.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.postify.blog.model.RegisterUser;

public interface UserRegistrationRepository extends MongoRepository<RegisterUser, String> {

    Optional<RegisterUser> findByUserId(String userId);
    
    Optional<RegisterUser> findByUsername(String username);

}
