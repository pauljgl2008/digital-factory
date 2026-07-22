package com.scotiabank.domain.aggregates;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@EqualsAndHashCode
public class Student {

    private final String id;

    private final String name;

    private final String lastname;

    private final Status status;

    private final Integer age;

}
