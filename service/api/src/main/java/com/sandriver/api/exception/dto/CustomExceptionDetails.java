package com.sandriver.api.exception.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class CustomExceptionDetails {
    private final String message;
    private final String errorCode;
    private final String path;
    private final HttpStatus status;
}