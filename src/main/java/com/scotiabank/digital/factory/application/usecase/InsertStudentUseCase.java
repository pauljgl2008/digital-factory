package com.scotiabank.digital.factory.application.usecase;

import com.scotiabank.digital.factory.infrastructure.adapters.in.controller.student.exception.InvalidFieldException;
import com.scotiabank.digital.factory.domain.model.Student;
import com.scotiabank.digital.factory.domain.ports.in.InsertStudentInputPort;
import com.scotiabank.digital.factory.domain.ports.out.FindStudentByIdOutputPort;
import com.scotiabank.digital.factory.domain.ports.out.InsertStudentOutputPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
                    if (studentExist) {
                        throw new InvalidFieldException(HttpStatus.BAD_REQUEST, "id", student.getId(),
                                "Usuario ya existe en BD");
                    } else {
                        return this.insertStudentOutputPort.insert(student)
                                .onErrorResume(error -> {
                                    log.error("Error al insertar el estudiante 1: " + error.getMessage());
                                    throw new InvalidFieldException(HttpStatus.CONFLICT, "id", student.getId(),
                                            error.getMessage());
                                });
                    }
                })
                .onErrorResume(error -> {
                            log.error("Error al insertar el estudiante 2: " + error.getMessage());
                            throw new InvalidFieldException(HttpStatus.CONFLICT, "id", student.getId(),
                                    error.getMessage());
                        }
                );
    }
}
