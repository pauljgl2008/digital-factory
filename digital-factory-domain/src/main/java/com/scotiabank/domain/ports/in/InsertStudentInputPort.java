package com.scotiabank.domain.ports.in;

import com.scotiabank.domain.exception.DuplicateIdException;
import com.scotiabank.domain.model.Student;
import reactor.core.publisher.Mono;

public interface InsertStudentInputPort {

    Mono<String> insert(Student student);

}
