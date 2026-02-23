package com.scotiabank.domain.ports.out;

import com.scotiabank.domain.aggregates.Student;
import reactor.core.publisher.Mono;

public interface FindStudentByIdOutputPort {

    Mono<Student> findById(String id);

}
