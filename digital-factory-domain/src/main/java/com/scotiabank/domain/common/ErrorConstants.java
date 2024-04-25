package com.scotiabank.domain.common;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ErrorConstants {

    public static final String INVALID_FIELD_ERROR_MESSAGE = "Error de validación de campos";

    public static final String STUDENT_INSERTION_ERROR_FORMAT = "Error al intentar insertar el alumno con ID='%s' debido a: %s.";

    public static final String STUDENT_INSERTION_ERROR_MESSAGE = "Se produjo un error durante la inserción del alumno.";

    public static final String STUDENT_INSERTION_CONFLICT_ERROR_MESSAGE = "No se pudo completar la inserción del alumno.";

}
