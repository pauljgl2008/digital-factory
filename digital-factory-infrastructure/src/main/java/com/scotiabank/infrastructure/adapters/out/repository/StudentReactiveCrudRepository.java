package com.scotiabank.infrastructure.adapters.out.repository;

import com.scotiabank.infrastructure.adapters.out.repository.entity.StudentEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface StudentReactiveCrudRepository extends ReactiveCrudRepository<StudentEntity, String> {

    @Query(Constants.QUERY_INSERT)
    Mono<Void> insertStudent(String id, String nombre, String apellido, Boolean estado, Integer edad);
}
