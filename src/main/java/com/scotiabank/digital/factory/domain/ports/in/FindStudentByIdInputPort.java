package com.scotiabank.digital.factory.domain.ports.in;

import com.scotiabank.digital.factory.domain.model.Student;
import reactor.core.publisher.Mono;

public interface FindStudentByIdInputPort {

    Mono<Student> findById(String id);

}
