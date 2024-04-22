package com.scotiabank.domain.ports.out;

import com.scotiabank.domain.model.Student;
import reactor.core.publisher.Mono;

public interface FindStudentByIdOutputPort {

    Mono<Student> findById(String id);

}
