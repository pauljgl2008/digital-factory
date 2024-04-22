package com.scotiabank.domain.exception;

public class ApplicationRuntimeException extends RuntimeException {
    private static final String DETAIL_FORMAT = "Ha ocurrido un error inesperado, message: %s";

    public ApplicationRuntimeException(final String message) {
        super(String.format(DETAIL_FORMAT, message));
    }
}
