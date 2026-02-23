package com.scotiabank.domain.common;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidationConstants {

    public static final String STUDENT_ID_FIELD = "id";

    public static final String STUDENT_STATUS_FIELD = "estado";

    public static final String STUDENT_ID_ALREADY_EXISTS_MESSAGE = "El id del alumno ya existe en base de datos.";

    public static final String INVALID_STUDENT_STATUS_MESSAGE = "El estado del alumno solo puede ser 'activo' o 'inactivo'.";

}
