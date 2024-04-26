package com.scotiabank.domain.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@EqualsAndHashCode
public class Student {

    private String id;

    private String nombre;

    private String apellido;

    private Status estado;

    private Integer edad;

}
