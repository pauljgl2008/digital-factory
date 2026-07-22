package com.scotiabank.domain.exception;

import org.springframework.http.HttpStatusCode;

public class StudentIdAlreadyExistsException extends StudentException {

    public StudentIdAlreadyExistsException(HttpStatusCode statusCode, String fieldName, String rejectedValue, String message) {
        super(statusCode, fieldName, rejectedValue, message);
    }

}
