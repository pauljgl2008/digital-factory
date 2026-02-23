package com.scotiabank.infrastructure.adapters.in.controller.common;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidationConstants {

    public static final String STUDENT_ID_REQUIRED_MESSAGE = "El campo id es requerido";
    public static final String STUDENT_NAME_REQUIRED_MESSAGE = "El campo nombre es requerido";
    public static final String STUDENT_LASTNAME_REQUIRED_MESSAGE = "El campo apellido es requerido";
    public static final String STUDENT_STATUS_REQUIRED_MESSAGE = "El campo estado es requerido";
    public static final String STUDENT_AGE_REQUIRED_MESSAGE = "El campo edad es requerido";
    public static final String STUDENT_ID_LENGTH_EXCEEDED_MESSAGE = "El campo id no debe exceder los 8 caracteres";
    public static final String STUDENT_NAME_LENGTH_EXCEEDED_MESSAGE = "El campo nombre no debe exceder los 10 caracteres";
    public static final String STUDENT_LASTNAME_LENGTH_EXCEEDED_MESSAGE = "El campo apellido no debe exceder los 30 caracteres";
    public static final String STUDENT_AGE_MUST_BE_NON_NEGATIVE_MESSAGE = "El campo edad debe ser mayor o igual a 3";
    public static final String STUDENT_AGE_EXCEEDED_MAXIMUM_MESSAGE = "El campo edad no debe exceder los 100 años";
    public static final String STUDENT_STATUS_INVALID_FORMAT_MESSAGE = "El campo estado solo puede tener valor de 'activo' o 'inactivo'";
    public static final String STUDENT_STATUS_REGEX_PATTERN = "(activo|inactivo)";

    public static final String NAME_PARAM = "nombre";
    public static final String LASTNAME_PARAM = "apellido";
    public static final String STATUS_PARAM = "estado";
    public static final String AGE_PARAM = "edad";

}
