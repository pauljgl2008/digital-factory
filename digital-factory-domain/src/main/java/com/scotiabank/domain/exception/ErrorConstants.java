package com.scotiabank.domain.exception;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ErrorConstants {

    public static final String STUDENT_INSERTION_ERROR_FORMAT = "Error al intentar insertar el alumno con ID='%s' debido a: %s.";

    public static final String STUDENT_INSERTION_ERROR_MESSAGE = "Se produjo un error durante la inserción del alumno.";

}
