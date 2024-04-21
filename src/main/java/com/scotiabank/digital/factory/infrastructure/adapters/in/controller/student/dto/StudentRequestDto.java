package com.scotiabank.digital.factory.infrastructure.adapters.in.controller.student.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class StudentRequestDto {

    @NotBlank(message = "El campo id es requerido")
    @Size(max = 8, message = "El campo id no debe exceder los 8 caracteres")
    private String id;

    @NotBlank(message = "El campo nombre es requerido")
    @Size(max = 10, message = "El campo nombre no debe exceder los 10 caracteres")
    private String nombre;

    @NotBlank(message = "El campo apellido es requerido")
    @Size(max = 30, message = "El campo apellido no debe exceder los 30 caracteres")
    private String apellido;

    @NotNull(message = "El campo estado es requerido")
    private Boolean estado;

    @NotNull(message = "El campo edad es requerido")
    @Min(value = 0, message = "El campo edad debe ser mayor o igual a 0")
    @Max(value = 100, message = "El campo edad no debe exceder los 100 años")
    private Integer edad;

}
