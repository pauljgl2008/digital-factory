package com.scotiabank.domain.ports.out;

import com.scotiabank.domain.aggregates.Student;
import reactor.core.publisher.Flux;

public interface GetAllStudentsOutputPort {

    Flux<Student> getAll();

}
