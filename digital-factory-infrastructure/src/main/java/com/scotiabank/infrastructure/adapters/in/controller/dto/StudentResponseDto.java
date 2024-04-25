package com.scotiabank.infrastructure.adapters.in.controller.dto;

import lombok.Data;

@Data
public class StudentResponseDto {

    private String id;

    private String nombre;

    private String apellido;

    private String estado;

    private Integer edad;
}
