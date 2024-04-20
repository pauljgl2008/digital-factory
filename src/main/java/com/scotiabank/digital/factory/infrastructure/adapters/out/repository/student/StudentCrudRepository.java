package com.scotiabank.digital.factory.infrastructure.adapters.out.repository.student;

import com.scotiabank.digital.factory.infrastructure.adapters.out.repository.student.entity.StudentEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface StudentCrudRepository extends ReactiveCrudRepository<StudentEntity, String> {

    @Query(Constants.QUERY_INSERT)
    Mono<Void> insertStudent(String id, String nombre, String apellido, Boolean estado, Integer edad);
}
