package com.scotiabank.infrastructure.adapters.out.repository.common;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StudentSqlQueries {

    public static final String QUERY_INSERT = "INSERT INTO STUDENT (id, name, lastname, status, age) values (:id,:name,:lastname,:status,:age)";

}
