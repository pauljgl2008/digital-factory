package com.scotiabank.domain.model;

import lombok.Data;

@Data
public class Student {

    private String id;

    private String nombre;

    private String apellido;

    private Status estado;

    private Integer edad;

}
