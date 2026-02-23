package com.scotiabank.infrastructure.adapters.in.controller.exception;

import com.scotiabank.domain.common.ErrorConstants;
import com.scotiabank.domain.exception.StudentCreationConflictException;
import com.scotiabank.domain.exception.StudentIdAlreadyExistsException;
import com.scotiabank.domain.exception.StudentStatusException;
import com.scotiabank.infrastructure.adapters.in.controller.exception.dto.ErrorDto;
import com.scotiabank.infrastructure.adapters.in.controller.exception.dto.ErrorResponseDto;
import io.netty.util.internal.StringUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    @NonNull
    protected Mono<ResponseEntity<Object>> handleWebExchangeBindException(final WebExchangeBindException ex,
                                                                          @NonNull final HttpHeaders headers,
                                                                          @NonNull final HttpStatusCode status,
                                                                          @NonNull final ServerWebExchange exchange) {
        return this.retrieveBadRequest(HttpStatus.BAD_REQUEST, ErrorConstants.INVALID_FIELD_ERROR_MESSAGE,
                ex.getBindingResult().getFieldErrors());
    }

    @ExceptionHandler(StudentIdAlreadyExistsException.class)
    public final Mono<ResponseEntity<Object>> handleException(final StudentIdAlreadyExistsException ex) {
        return this.retrieveBadRequest(ex.getStatusCode(), ErrorConstants.STUDENT_INSERTION_ERROR_MESSAGE,
                List.of(this.buildFieldError(ex.getFieldName(), ex.getRejectedValue(), ex.getMessage())));
    }

    @ExceptionHandler(StudentCreationConflictException.class)
    public final Mono<ResponseEntity<Object>> handleException(final StudentCreationConflictException ex) {
        return this.retrieveBadRequest(ex.getStatusCode(), ErrorConstants.STUDENT_INSERTION_ERROR_MESSAGE,
                List.of(this.buildFieldError(ex.getFieldName(), ex.getRejectedValue(), ex.getMessage())));
    }

    @ExceptionHandler(StudentStatusException.class)
    public final Mono<ResponseEntity<Object>> handleException(final StudentStatusException ex) {
        return this.retrieveBadRequest(ex.getStatusCode(),
                ErrorConstants.INVALID_FIELD_ERROR_MESSAGE,
                List.of(this.buildFieldError(ex.getFieldName(), ex.getRejectedValue(), ex.getMessage())));
    }

    private Mono<ResponseEntity<Object>> retrieveBadRequest(HttpStatusCode status, String type,
                                                            List<FieldError> fieldErrors) {
        List<ErrorDto> errors = fieldErrors.stream()
                .map(err -> new ErrorDto(err.getField(), err.getRejectedValue(), err.getDefaultMessage()))
                .toList();

        ErrorResponseDto response = new ErrorResponseDto(status.value(),
                HttpStatus.valueOf(status.value()).getReasonPhrase(), type, errors);

        return Mono.just(ResponseEntity.status(status.value()).body(response));
    }

    private FieldError buildFieldError(final String fieldName, final String rejectedValue, final String message) {
        return new FieldError(StringUtil.EMPTY_STRING, fieldName, rejectedValue, true, null,
                null, message);
    }

}
