package com.scotiabank.domain.common;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ErrorConstants {

    public static final String INVALID_FIELD_ERROR_MESSAGE = "Field validation error";

    public static final String STUDENT_INSERTION_ERROR_FORMAT = "Error inserting student with ID='%s': %s.";

    public static final String STUDENT_INSERTION_ERROR_MESSAGE = "An error occurred while inserting the student.";

    public static final String STUDENT_INSERTION_CONFLICT_ERROR_MESSAGE = "Could not complete the student insertion.";

}
