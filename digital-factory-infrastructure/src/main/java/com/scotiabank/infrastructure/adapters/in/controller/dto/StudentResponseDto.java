package com.scotiabank.infrastructure.adapters.in.controller.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class StudentResponseDto {

    private String id;

    private String nombre;

    private String apellido;

    private String estado;

    private Integer edad;

}
