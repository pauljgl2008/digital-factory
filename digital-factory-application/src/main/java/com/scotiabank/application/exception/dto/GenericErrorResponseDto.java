package com.scotiabank.application.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenericErrorResponseDto {

    private int status;

    private String error;

    private String message;

    private String field;

    private Object rejectedValue;

}