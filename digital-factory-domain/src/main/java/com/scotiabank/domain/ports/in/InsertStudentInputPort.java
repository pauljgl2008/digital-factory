package com.scotiabank.domain.ports.in;

import com.scotiabank.domain.aggregates.Student;
import reactor.core.publisher.Mono;

public interface InsertStudentInputPort {

    Mono<Void> insert(Student student);

}
