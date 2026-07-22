package com.scotiabank.infrastructure.adapters.in.controller.exception;

import com.scotiabank.domain.common.ErrorConstants;
import com.scotiabank.domain.exception.StudentCreationConflictException;
import com.scotiabank.domain.exception.StudentIdAlreadyExistsException;
import com.scotiabank.domain.exception.StudentStatusException;
import com.scotiabank.infrastructure.adapters.in.controller.exception.dto.ErrorDto;
import com.scotiabank.infrastructure.adapters.in.controller.exception.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(WebExchangeBindException.class)
    public Mono<ResponseEntity<Object>> handleValidationExceptions(WebExchangeBindException ex) {
        List<ErrorDto> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> new ErrorDto(err.getField(), err.getRejectedValue(), err.getDefaultMessage()))
                .toList();
        return buildResponse(HttpStatus.BAD_REQUEST, ErrorConstants.INVALID_FIELD_ERROR_MESSAGE, errors);
    }

    @ExceptionHandler(StudentIdAlreadyExistsException.class)
    public Mono<ResponseEntity<Object>> handleStudentIdAlreadyExists(StudentIdAlreadyExistsException ex) {
        return buildErrorResponse(ex.getStatusCode(), ErrorConstants.STUDENT_INSERTION_ERROR_MESSAGE,
                ex.getFieldName(), ex.getRejectedValue(), ex.getMessage());
    }

    @ExceptionHandler(StudentCreationConflictException.class)
    public Mono<ResponseEntity<Object>> handleStudentCreationConflict(StudentCreationConflictException ex) {
        return buildErrorResponse(ex.getStatusCode(), ErrorConstants.STUDENT_INSERTION_ERROR_MESSAGE,
                ex.getFieldName(), ex.getRejectedValue(), ex.getMessage());
    }

    @ExceptionHandler(StudentStatusException.class)
    public Mono<ResponseEntity<Object>> handleStudentStatus(StudentStatusException ex) {
        return buildErrorResponse(ex.getStatusCode(), ErrorConstants.INVALID_FIELD_ERROR_MESSAGE,
                ex.getFieldName(), ex.getRejectedValue(), ex.getMessage());
    }

    private Mono<ResponseEntity<Object>> buildErrorResponse(HttpStatusCode status, String message,
                                                             String fieldName, Object rejectedValue, String detail) {
        List<ErrorDto> errors = List.of(new ErrorDto(fieldName, rejectedValue, detail));
        return buildResponse(status, message, errors);
    }

    private Mono<ResponseEntity<Object>> buildResponse(HttpStatusCode status, String message,
                                                        List<ErrorDto> errors) {
        ErrorResponseDto response = new ErrorResponseDto(
                status.value(),
                HttpStatus.valueOf(status.value()).getReasonPhrase(),
                message,
                errors);
        return Mono.just(ResponseEntity.status(status.value()).body(response));
    }

}
