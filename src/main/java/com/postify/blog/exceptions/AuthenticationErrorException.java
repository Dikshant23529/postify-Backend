package com.postify.blog.exceptions;

public class AuthenticationErrorException extends RuntimeException {

    public AuthenticationErrorException(String message) {
        super(message);
    }

}
