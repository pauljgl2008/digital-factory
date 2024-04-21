package com.scotiabank.digital.factory.infrastructure.adapters.in.controller.student.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenericErrorResponseDto {

    private int status;

    private String error;

    private String message;

}