package com.scotiabank.digital.factory.infrastructure.adapters.out.repository.student;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {

    public static final String QUERY_INSERT = "INSERT INTO STUDENT (id, nombre, apellido, estado, edad) values (:id,:nombre,:apellido,:estado,:edad)";

}
