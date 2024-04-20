package com.scotiabank.digital.factory.domain.model;

import lombok.Data;

@Data
public class Student {

    private String id;

    private String nombre;

    private String apellido;

    private Boolean estado;

    private Integer edad;

}
