package com.scotiabank.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatusCode;

import java.io.Serial;

@Getter
public class StudentCreationConflictException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 39348712723127893L;

    private final HttpStatusCode statusCode;

    private final String fieldName;

    private final String rejectedValue;


    public StudentCreationConflictException(final HttpStatusCode statusCode, final String fieldName, final String rejectedValue, final String message) {
        super(message);
        this.fieldName = fieldName;
        this.rejectedValue = rejectedValue;
        this.statusCode = statusCode;
    }
}