package com.scotiabank.infrastructure.adapters.in.controller.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorDto {

    private String field;

    private Object rejectedValue;

    private String message;

}
