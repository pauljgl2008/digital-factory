package com.scotiabank.digital.factory.adapters.in.controller.student.dto;

import lombok.Data;

@Data
public class StudentResponseDto {

    private String id;

    private String nombre;

    private String apellido;

    private Boolean estado;

    private Integer edad;
}
