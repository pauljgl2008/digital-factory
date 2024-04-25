package com.scotiabank.infrastructure.adapters.in.controller.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@AllArgsConstructor
@Getter
@Setter
public class ErrorResponseDto {

    private int status;

    private String error;

    private String message;

    private List<ErrorDto> details;

}