package com.scotiabank.infrastructure.adapters.out.repository.common;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StudentSQLQueries {

    public static final String QUERY_INSERT = "INSERT INTO STUDENT (id, nombre, apellido, estado, edad) values (:id,:nombre,:apellido,:estado,:edad)";

}
