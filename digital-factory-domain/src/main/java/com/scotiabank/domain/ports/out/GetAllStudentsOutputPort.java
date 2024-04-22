package com.scotiabank.domain.ports.out;

import com.scotiabank.domain.model.Student;
import reactor.core.publisher.Flux;

public interface GetAllStudentsOutputPort {

    Flux<Student> getAll();

}
