package com.scotiabank.infrastructure.adapters.out.repository;

import com.scotiabank.infrastructure.adapters.out.repository.entity.StudentEntity;
import com.scotiabank.infrastructure.adapters.out.repository.common.StudentSqlQueries;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface StudentReactiveRepository extends ReactiveCrudRepository<StudentEntity, String> {

    @Query(StudentSqlQueries.QUERY_INSERT)
    Mono<Void> insertStudent(String id, String name, String lastname, String status, Integer age);

}
