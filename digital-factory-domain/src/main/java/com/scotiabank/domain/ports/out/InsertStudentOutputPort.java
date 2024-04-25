package com.scotiabank.domain.ports.out;

import com.scotiabank.domain.model.Student;
import reactor.core.publisher.Mono;

public interface InsertStudentOutputPort {

    Mono<Void> insert(Student student);

}
