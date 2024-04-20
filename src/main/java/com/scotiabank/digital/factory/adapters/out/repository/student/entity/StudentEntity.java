package com.scotiabank.digital.factory.adapters.out.repository.student.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table("STUDENT")
public class StudentEntity {

    @Id
    private String id;

    private String nombre;

    private String apellido;

    private Boolean estado;

    private Integer edad;

}
