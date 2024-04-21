package com.scotiabank.digital.factory.application.usecase;

import com.scotiabank.digital.factory.domain.model.Student;
import com.scotiabank.digital.factory.domain.ports.in.InsertStudentInputPort;
import com.scotiabank.digital.factory.domain.ports.out.FindStudentByIdOutputPort;
import com.scotiabank.digital.factory.domain.ports.out.InsertStudentOutputPort;
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
    public Mono<MessageResponseDto> insert(Student student) {
        return this.findStudentByIdOutputPort.findById(student.getId())
                .hasElement()
                .flatMap(studentExist -> {
                    if (studentExist) {
                        return Mono.just(new MessageResponseDto("El estudiante ya existe en la base de datos"));
                    } else {
                        return this.insertStudentOutputPort.insert(student)
                                .thenReturn(new MessageResponseDto("Estudiante insertado exitosamente"))
                                .onErrorResume(error -> {
                                    log.error("Error al insertar el estudiante: " + error.getMessage());
                                    return Mono.just(new MessageResponseDto("Error al insertar el estudiante: " + error.getMessage()));
                                });
                    }
                })
                .onErrorResume(error -> {
                    return Mono.just(new MessageResponseDto("Error al procesar la inserción del estudiante"));
                });
    }
}
