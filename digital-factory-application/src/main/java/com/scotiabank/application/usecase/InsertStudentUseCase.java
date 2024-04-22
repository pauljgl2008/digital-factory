package com.scotiabank.application.usecase;

import com.scotiabank.domain.exception.ApplicationRuntimeException;
import com.scotiabank.domain.exception.DuplicateIdException;
import com.scotiabank.domain.model.Student;
import com.scotiabank.domain.ports.in.InsertStudentInputPort;
import com.scotiabank.domain.ports.out.FindStudentByIdOutputPort;
import com.scotiabank.domain.ports.out.InsertStudentOutputPort;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class InsertStudentUseCase implements InsertStudentInputPort {
    private final FindStudentByIdOutputPort findStudentByIdOutputPort;

    private final InsertStudentOutputPort insertStudentOutputPort;

    public InsertStudentUseCase(FindStudentByIdOutputPort findStudentByIdOutputPort,
                                InsertStudentOutputPort insertStudentOutputPort) {
        this.findStudentByIdOutputPort = findStudentByIdOutputPort;
        this.insertStudentOutputPort = insertStudentOutputPort;
    }

    @Override
    public Mono<String> insert(Student student) {
        return this.findStudentByIdOutputPort.findById(student.getId())
                .hasElement()
                .flatMap(studentExist -> {
                    if (studentExist.equals(true)) {
                        return Mono.error(new DuplicateIdException(student.getId()));
                    } else {
                        this.insertStudentOutputPort.insert(student)
                                .onErrorResume(error -> {
                                    log.error(error.getMessage());
                                    throw new ApplicationRuntimeException("Error en la inserción del alumno");
                                });
                    }
                    return Mono.empty();
                });
    }
}
