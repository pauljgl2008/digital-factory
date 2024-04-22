package com.scotiabank.infrastructure.adapters.in.controller.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDto {

    private String field;

    private Object rejectedValue;

    private String message;

}
