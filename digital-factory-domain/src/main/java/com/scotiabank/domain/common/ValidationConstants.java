package com.scotiabank.domain.common;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidationConstants {

    public static final String STUDENT_ID_FIELD = "id";

    public static final String STUDENT_STATUS_FIELD = "status";

    public static final String STUDENT_ID_ALREADY_EXISTS_MESSAGE = "The student ID already exists in the database.";

    public static final String INVALID_STUDENT_STATUS_MESSAGE = "Student status must be 'active' or 'inactive'.";

}
