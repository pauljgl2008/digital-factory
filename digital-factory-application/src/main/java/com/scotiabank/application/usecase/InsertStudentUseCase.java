package com.scotiabank.application.usecase;

import com.scotiabank.application.exception.UseCaseException;
import com.scotiabank.domain.model.Student;
import com.scotiabank.domain.ports.in.InsertStudentInputPort;
import com.scotiabank.domain.ports.out.FindStudentByIdOutputPort;
import com.scotiabank.domain.ports.out.InsertStudentOutputPort;
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
    public Mono<Void> insert(Student student) {
        return this.findStudentByIdOutputPort.findById(student.getId())
                .hasElement()
                .flatMap(studentExist -> {
                    if (studentExist.equals(true)) {
                        throw new UseCaseException(HttpStatus.BAD_REQUEST, "id", student.getId(),
                                "El alumno ya existe en BD");
                    } else {
                        return this.insertStudentOutputPort.insert(student)
                                .onErrorResume(error -> {
                                    log.error(error.getMessage());
                                    throw new UseCaseException(HttpStatus.CONFLICT, "id", student.getId(),
                                            "Error en la inserción del alumno");
                                });
                    }
                });
    }
}
