package com.scotiabank.infrastructure.adapters.out.repository.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Builder
@Table("STUDENT")
public class StudentEntity {

    @Id
    private String id;

    private String nombre;

    private String apellido;

    private String estado;

    private Integer edad;

}
