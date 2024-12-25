package com.postify.blog.exceptions;

public class DuplicateEntriesException extends RuntimeException {

    public DuplicateEntriesException(String message) {
        super(message);
    }

}
