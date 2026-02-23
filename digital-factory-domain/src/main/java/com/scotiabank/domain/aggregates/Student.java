package com.scotiabank.domain.aggregates;

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

    private String name;

    private String lastname;

    private Status status;

    private Integer age;

}
