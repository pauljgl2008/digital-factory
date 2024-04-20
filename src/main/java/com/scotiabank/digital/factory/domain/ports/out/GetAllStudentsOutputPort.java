package com.scotiabank.digital.factory.domain.ports.out;

import com.scotiabank.digital.factory.domain.model.Student;
import reactor.core.publisher.Flux;

public interface GetAllStudentsOutputPort {

    Flux<Student> getAll();

}
