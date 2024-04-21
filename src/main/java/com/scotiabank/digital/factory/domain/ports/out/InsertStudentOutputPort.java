package com.scotiabank.digital.factory.domain.ports.out;

import com.scotiabank.digital.factory.domain.model.Student;
import reactor.core.publisher.Mono;

public interface InsertStudentOutputPort {

    Mono<Void> insert(Student student);
}
