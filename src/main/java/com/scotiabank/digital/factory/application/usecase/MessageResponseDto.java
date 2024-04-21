package com.scotiabank.digital.factory.application.usecase;

import lombok.Data;

@Data
public class MessageResponseDto {
    private String message;

    public MessageResponseDto(String message) {
        this.message = message;
    }

    // Getters y Setters (opcional)
}
