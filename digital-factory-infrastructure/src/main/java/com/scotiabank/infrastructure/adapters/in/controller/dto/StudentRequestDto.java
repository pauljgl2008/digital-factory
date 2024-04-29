package com.scotiabank.infrastructure.adapters.in.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import static com.scotiabank.infrastructure.adapters.in.controller.common.ValidationConstants.*;

@Builder
@Getter
@Setter
public class StudentRequestDto {

    @NotBlank(message = STUDENT_ID_REQUIRED_MESSAGE)
    @Size(max = 8, message = STUDENT_ID_LENGTH_EXCEEDED_MESSAGE)
    private String id;

    @NotBlank(message = STUDENT_NAME_REQUIRED_MESSAGE)
    @Size(max = 15, message = STUDENT_NAME_LENGTH_EXCEEDED_MESSAGE)
    @JsonProperty(NAME_PARAM)
    private String name;

    @NotBlank(message = STUDENT_LASTNAME_REQUIRED_MESSAGE)
    @Size(max = 30, message = STUDENT_LASTNAME_LENGTH_EXCEEDED_MESSAGE)
    @JsonProperty(LASTNAME_PARAM)
    private String lastname;

    @NotNull(message = STUDENT_STATUS_REQUIRED_MESSAGE)
    @JsonProperty(STATUS_PARAM)
    private String status;

    @NotNull(message = STUDENT_AGE_REQUIRED_MESSAGE)
    @Min(value = 0, message = STUDENT_AGE_MUST_BE_NON_NEGATIVE_MESSAGE)
    @Max(value = 100, message = STUDENT_AGE_EXCEEDED_MAXIMUM_MESSAGE)
    @JsonProperty(AGE_PARAM)
    private Integer age;

}
