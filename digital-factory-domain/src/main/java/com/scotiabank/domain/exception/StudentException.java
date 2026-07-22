package com.scotiabank.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatusCode;

import java.io.Serial;

@Getter
public abstract class StudentException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 39348712723127893L;

    private final HttpStatusCode statusCode;
    private final String fieldName;
    private final String rejectedValue;

    protected StudentException(HttpStatusCode statusCode, String fieldName, String rejectedValue, String message) {
        super(message);
        this.statusCode = statusCode;
        this.fieldName = fieldName;
        this.rejectedValue = rejectedValue;
    }

}
