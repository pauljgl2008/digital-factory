package com.scotiabank.digital.factory.application.usecase;

import com.scotiabank.digital.factory.domain.model.Student;
import com.scotiabank.digital.factory.domain.ports.in.FindStudentByIdInputPort;
import com.scotiabank.digital.factory.domain.ports.out.FindStudentByIdOutputPort;
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
