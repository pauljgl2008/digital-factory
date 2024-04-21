package com.scotiabank.digital.factory.application.usecase;

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
