package com.scotiabank.domain.exception;

import org.springframework.http.HttpStatusCode;

public class StudentStatusException extends StudentException {

    public StudentStatusException(HttpStatusCode statusCode, String fieldName, String rejectedValue, String message) {
        super(statusCode, fieldName, rejectedValue, message);
    }

}
