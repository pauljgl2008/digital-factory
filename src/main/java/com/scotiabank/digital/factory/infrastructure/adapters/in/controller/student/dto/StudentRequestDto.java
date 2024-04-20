package com.scotiabank.digital.factory.infrastructure.adapters.in.controller.student.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StudentRequestDto {

    private String id;

    @NotBlank
    private String nombre;

    @NotBlank
    private String apellido;

    @NotNull
    private Boolean estado;

    @NotNull
    private Integer edad;
}
