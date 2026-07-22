package com.scotiabank.infrastructure.adapters.in.controller.exception.dto;

public record ErrorDto(String field, Object rejectedValue, String message) {

}
