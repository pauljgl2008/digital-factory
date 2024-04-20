package com.scotiabank.digital.factory.domain.ports.out;

import com.scotiabank.digital.factory.domain.model.Student;
import reactor.core.publisher.Mono;

public interface FindStudentByIdOutputPort {

    Mono<Student> findById(String id);

}
