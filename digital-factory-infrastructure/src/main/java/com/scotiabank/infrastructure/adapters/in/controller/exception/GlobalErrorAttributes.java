package com.scotiabank.infrastructure.adapters.in.controller.exception;

import com.scotiabank.domain.exception.DuplicateIdException;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.Map;

@Component
public class GlobalErrorAttributes extends DefaultErrorAttributes {

    public static final String STATUS_PARAM = "status";
    public static final String MESSAGE_PARAM = "message";
    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request,
                                                  ErrorAttributeOptions options) {
        Map<String, Object> map = super.getErrorAttributes(request, options);
        map.put(STATUS_PARAM, HttpStatus.BAD_REQUEST);
        Throwable throwable = getError(request);
        if (throwable instanceof DuplicateIdException) {
            map.put(MESSAGE_PARAM, throwable.getMessage());
        } else {
            map.put(MESSAGE_PARAM, map.getOrDefault(MESSAGE_PARAM, "An error occurred"));
        }
        return map;
    }

}

