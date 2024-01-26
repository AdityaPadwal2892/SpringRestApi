package com.codewithaditya.fullstackbackend.exception;
import java.util.Optional;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long id) {
        super("User not found with id: " + id);
    }
}
