package com.scotiabank.domain.ports.in;

import com.scotiabank.domain.aggregates.Student;
import reactor.core.publisher.Flux;

public interface GetAllStudentsInputPort {

    Flux<Student> getAll();

}
