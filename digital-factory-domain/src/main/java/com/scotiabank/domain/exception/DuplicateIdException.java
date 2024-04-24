package com.scotiabank.domain.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class DuplicateIdException extends Exception {

    private final HttpStatus status;

    public DuplicateIdException(final HttpStatus status, final String message) {
        super(message);
        this.status = status;
    }
}
