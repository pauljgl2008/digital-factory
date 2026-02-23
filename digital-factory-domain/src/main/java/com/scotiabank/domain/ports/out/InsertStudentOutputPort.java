package com.scotiabank.domain.ports.out;

import com.scotiabank.domain.aggregates.Student;
import reactor.core.publisher.Mono;

public interface InsertStudentOutputPort {

    Mono<Void> insert(Student student);

}
