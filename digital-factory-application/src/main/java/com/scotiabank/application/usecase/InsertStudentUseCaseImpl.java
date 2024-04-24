package com.scotiabank.application.usecase;

import com.scotiabank.domain.exception.ApplicationRuntimeException;
import com.scotiabank.domain.exception.DuplicateIdException;
import com.scotiabank.domain.model.Student;
import com.scotiabank.domain.ports.in.InsertStudentInputPort;
import com.scotiabank.domain.ports.out.FindStudentByIdOutputPort;
import com.scotiabank.domain.ports.out.InsertStudentOutputPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Mono;

@Slf4j
public class InsertStudentUseCaseImpl implements InsertStudentInputPort {
    private final FindStudentByIdOutputPort findStudentByIdOutputPort;

    private final InsertStudentOutputPort insertStudentOutputPort;

    public InsertStudentUseCaseImpl(FindStudentByIdOutputPort findStudentByIdOutputPort,
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
                        return Mono.error(new DuplicateIdException(HttpStatus.BAD_REQUEST, String.format("El id %s ya está registrado", student.getId())));
                    } else {
                        return this.insertStudentOutputPort.insert(student)
                                .thenReturn("Alumno insertado exitosamente")
                                .onErrorResume(error -> {
                                    log.error(error.getMessage());
                                    return Mono.error(new ApplicationRuntimeException("Error en la inserción del alumno"));
                                });
                    }
                });
    }
}
