package com.postify.blog.exceptions;

public class AuthorizationErrorException extends RuntimeException {

    public AuthorizationErrorException(String message) {
        super(message);
    }

}
