package com.scotiabank.infrastructure.adapters.in.controller.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@EqualsAndHashCode
public class StudentResponseDto {

    private String id;

    private String name;

    private String lastname;

    private String status;

    private Integer age;

}
