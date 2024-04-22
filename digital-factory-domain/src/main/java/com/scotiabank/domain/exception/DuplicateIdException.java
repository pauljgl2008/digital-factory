package com.scotiabank.domain.exception;

public class DuplicateIdException extends Exception {

    private static final String DETAIL_FORMAT = "El id %s ya está registrado";

    public DuplicateIdException(final String id) {
        super(String.format(DETAIL_FORMAT, id));
    }
}
