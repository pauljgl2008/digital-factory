package com.scotiabank.digital.factory.infrastructure.adapters.in.controller.student.exception;

import com.scotiabank.digital.factory.infrastructure.adapters.in.controller.student.dto.ErrorDto;
import com.scotiabank.digital.factory.infrastructure.adapters.in.controller.student.dto.GenericErrorResponseDto;
import com.scotiabank.digital.factory.infrastructure.adapters.in.controller.student.dto.InvalidFieldErrorResponseDto;
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
    protected Mono<ResponseEntity<Object>> handleWebExchangeBindException(final WebExchangeBindException ex,
                                                                          @NonNull final HttpHeaders headers,
                                                                          @NonNull final HttpStatusCode status,
                                                                          @NonNull final ServerWebExchange exchange) {
        return this.retrieveBadRequest(ex.getBindingResult().getFieldErrors());
    }

    @ExceptionHandler({RepositoryException.class, UseCaseException.class})
    public final Mono<ResponseEntity<Object>> handleException(final RuntimeException ex) {
        HttpStatus statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
        String fieldName = null;
        String rejectedValue = null;

        if (ex instanceof RepositoryException repositoryException) {
            statusCode = HttpStatus.valueOf(((RepositoryException) ex).getStatusCode().value());
            fieldName = repositoryException.getFieldName();
            rejectedValue = repositoryException.getRejectedValue();
        } else if (ex instanceof UseCaseException useCaseException) {
            statusCode = HttpStatus.valueOf(((UseCaseException) ex).getStatusCode().value());
            fieldName = useCaseException.getFieldName();
            rejectedValue = useCaseException.getRejectedValue();
        }

        GenericErrorResponseDto response = GenericErrorResponseDto.builder()
                .status(statusCode.value())
                .error(statusCode.getReasonPhrase())
                .message(ex.getMessage())
                .field(fieldName)
                .rejectedValue(rejectedValue)
                .build();

        return Mono.just(ResponseEntity.status(statusCode).body(response));
    }


    @ExceptionHandler(InvalidFieldException.class)
    public final Mono<ResponseEntity<Object>> invalidFieldExceptionHandler(final InvalidFieldException ex) {
        return this.retrieveBadRequest(List.of(this.buildFieldError(ex.getFieldName(), ex.getRejectedValue(),
                ex.getMessage())));
    }

    private Mono<ResponseEntity<Object>> retrieveBadRequest(List<FieldError> fieldErrors) {
        List<ErrorDto> errors = fieldErrors.stream()
                .map(err -> new ErrorDto(err.getField(), err.getRejectedValue(), err.getDefaultMessage()))
                .toList();

        InvalidFieldErrorResponseDto response = new InvalidFieldErrorResponseDto(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "Validation failed",
                errors
        );

        return Mono.just(ResponseEntity.badRequest().body(response));
    }

    private FieldError buildFieldError(final String fieldName, final String rejectedValue, final String message) {
        return new FieldError("", fieldName, rejectedValue, true,
                null, null, message);
    }
}
