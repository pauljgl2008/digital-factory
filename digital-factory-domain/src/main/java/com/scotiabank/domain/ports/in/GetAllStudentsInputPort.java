package com.scotiabank.domain.ports.in;

import com.scotiabank.domain.model.Student;
import reactor.core.publisher.Flux;

public interface GetAllStudentsInputPort {

    Flux<Student> getAll();

}
