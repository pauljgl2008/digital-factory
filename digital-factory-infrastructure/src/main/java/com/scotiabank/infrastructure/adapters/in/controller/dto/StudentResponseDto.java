package com.scotiabank.infrastructure.adapters.in.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import static com.scotiabank.infrastructure.adapters.in.controller.common.ValidationConstants.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode
public class StudentResponseDto {

    private String id;

    @JsonProperty(NAME_PARAM)
    private String name;

    @JsonProperty(LASTNAME_PARAM)
    private String lastname;

    @JsonProperty(STATUS_PARAM)
    private String status;

    @JsonProperty(AGE_PARAM)
    private Integer age;

}
