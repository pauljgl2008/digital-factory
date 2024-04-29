package com.scotiabank.infrastructure.adapters.in.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class StudentRequestDto {

    @NotBlank(message = "El campo id es requerido")
    @Size(max = 8, message = "El campo id no debe exceder los 8 caracteres")
    private String id;

    @NotBlank(message = "El campo nombre es requerido")
    @Size(max = 15, message = "El campo nombre no debe exceder los 10 caracteres")
    @JsonProperty("nombre")
    private String name;

    @NotBlank(message = "El campo apellido es requerido")
    @Size(max = 30, message = "El campo apellido no debe exceder los 30 caracteres")
    @JsonProperty("apellido")
    private String lastname;

    @NotNull(message = "El campo estado es requerido")
    @JsonProperty("estado")
    private String status;

    @NotNull(message = "El campo edad es requerido")
    @Min(value = 0, message = "El campo edad debe ser mayor o igual a 0")
    @Max(value = 100, message = "El campo edad no debe exceder los 100 años")
    @JsonProperty("edad")
    private Integer age;

}
