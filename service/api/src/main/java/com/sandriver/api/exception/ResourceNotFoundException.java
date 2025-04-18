package com.sandriver.api.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends CustomException {

    public ResourceNotFoundException(String resourceName, String resourceId) {
        super(String.format("%s with ID %s not found", resourceName, resourceId),
                "RESOURCE_NOT_FOUND",
                HttpStatus.NOT_FOUND);
    }
}