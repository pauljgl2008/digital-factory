package com.scotiabank.digital.factory.domain.ports.in;

import com.scotiabank.digital.factory.application.usecase.MessageResponseDto;
import com.scotiabank.digital.factory.domain.model.Student;
import reactor.core.publisher.Mono;

public interface InsertStudentInputPort {

    Mono<String> insert(Student student);

}
