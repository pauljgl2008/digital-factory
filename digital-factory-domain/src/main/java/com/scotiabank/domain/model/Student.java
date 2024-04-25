package com.scotiabank.domain.model;

import lombok.Data;

@Data
public class Student {

    private String id;

    private String nombre;

    private String apellido;

    private Status estado;

    private Integer edad;

    public void setEstado(Status estado) {
        if (estado != Status.ACTIVE && estado != Status.INACTIVE) {
            throw new IllegalArgumentException("El estado debe ser 'activo' o 'inactivo'");
        }
        this.estado = estado;
    }

}
