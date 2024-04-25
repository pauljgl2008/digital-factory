package com.scotiabank.infrastructure.adapters.out.repository;

import com.scotiabank.infrastructure.adapters.out.repository.entity.StudentEntity;
import com.scotiabank.infrastructure.adapters.out.repository.common.StudentSQLQueries;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface StudentReactiveCrudRepository extends ReactiveCrudRepository<StudentEntity, String> {

    @Query(StudentSQLQueries.QUERY_INSERT)
    Mono<Void> insertStudent(String id, String nombre, String apellido, String estado, Integer edad);

}
