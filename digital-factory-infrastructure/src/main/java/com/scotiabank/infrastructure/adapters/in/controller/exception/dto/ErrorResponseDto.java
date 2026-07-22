package com.scotiabank.infrastructure.adapters.in.controller.exception.dto;

import java.util.List;

public record ErrorResponseDto(int status, String error, String message, List<ErrorDto> details) {

}
