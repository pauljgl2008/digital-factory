package com.scotiabank.infrastructure.adapters.out.repository;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {

    public static final String QUERY_INSERT = "INSERT INTO STUDENT (id, nombre, apellido, estado, edad) values (:id,:nombre,:apellido,:estado,:edad)";

}
