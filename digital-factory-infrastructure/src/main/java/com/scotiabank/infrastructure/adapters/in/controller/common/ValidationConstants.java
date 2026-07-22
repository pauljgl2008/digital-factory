package com.scotiabank.infrastructure.adapters.in.controller.common;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidationConstants {

    public static final String STUDENT_ID_REQUIRED_MESSAGE = "The field id is required";
    public static final String STUDENT_NAME_REQUIRED_MESSAGE = "The field name is required";
    public static final String STUDENT_LASTNAME_REQUIRED_MESSAGE = "The field lastname is required";
    public static final String STUDENT_STATUS_REQUIRED_MESSAGE = "The field status is required";
    public static final String STUDENT_AGE_REQUIRED_MESSAGE = "The field age is required";
    public static final String STUDENT_ID_LENGTH_EXCEEDED_MESSAGE = "The field id must not exceed 8 characters";
    public static final String STUDENT_NAME_LENGTH_EXCEEDED_MESSAGE = "The field name must not exceed 10 characters";
    public static final String STUDENT_LASTNAME_LENGTH_EXCEEDED_MESSAGE = "The field lastname must not exceed 30 characters";
    public static final String STUDENT_AGE_MUST_BE_NON_NEGATIVE_MESSAGE = "The field age must be greater or equal to 3";
    public static final String STUDENT_AGE_EXCEEDED_MAXIMUM_MESSAGE = "The field age must not exceed 100 years";
    public static final String STUDENT_STATUS_INVALID_FORMAT_MESSAGE = "The field status must be 'active' or 'inactive'";
    public static final String STUDENT_STATUS_REGEX_PATTERN = "(active|inactive)";

    public static final String NAME_PARAM = "name";
    public static final String LASTNAME_PARAM = "lastname";
    public static final String STATUS_PARAM = "status";
    public static final String AGE_PARAM = "age";

}
