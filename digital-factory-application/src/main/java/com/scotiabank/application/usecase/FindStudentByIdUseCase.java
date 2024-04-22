package com.scotiabank.application.usecase;

import com.scotiabank.domain.model.Student;
import com.scotiabank.domain.ports.in.FindStudentByIdInputPort;
import com.scotiabank.domain.ports.out.FindStudentByIdOutputPort;
import reactor.core.publisher.Mono;

public class FindStudentByIdUseCase implements FindStudentByIdInputPort {

    private final FindStudentByIdOutputPort findStudentByIdOutputPort;

    public FindStudentByIdUseCase(FindStudentByIdOutputPort findStudentByIdOutputPort) {
        this.findStudentByIdOutputPort = findStudentByIdOutputPort;
    }

    @Override
    public Mono<Student> findById(String id) {
        return this.findStudentByIdOutputPort.findById(id);
    }

}
