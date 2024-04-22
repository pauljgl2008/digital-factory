package com.scotiabank.domain.ports.in;

import com.scotiabank.domain.model.Student;
import reactor.core.publisher.Mono;

public interface FindStudentByIdInputPort {

    Mono<Student> findById(String id);

}
