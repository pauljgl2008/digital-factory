package com.scotiabank.domain.exception;

import org.springframework.http.HttpStatusCode;

public class StudentCreationConflictException extends StudentException {

    public StudentCreationConflictException(HttpStatusCode statusCode, String fieldName, String rejectedValue, String message) {
        super(statusCode, fieldName, rejectedValue, message);
    }

}
