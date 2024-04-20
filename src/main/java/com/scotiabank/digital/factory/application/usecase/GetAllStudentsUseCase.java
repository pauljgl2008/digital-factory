package com.scotiabank.digital.factory.application.usecase;

import com.scotiabank.digital.factory.domain.model.Student;
import com.scotiabank.digital.factory.domain.ports.in.GetAllStudentsInputPort;
import com.scotiabank.digital.factory.domain.ports.out.GetAllStudentsOutputPort;
import reactor.core.publisher.Flux;

public class GetAllStudentsUseCase implements GetAllStudentsInputPort {

    private final GetAllStudentsOutputPort getAllStudentsOutputPort;

    public GetAllStudentsUseCase(GetAllStudentsOutputPort getAllStudentsOutputPort) {
        this.getAllStudentsOutputPort = getAllStudentsOutputPort;
    }

    @Override
    public Flux<Student> getAll() {
        return this.getAllStudentsOutputPort.getAll();
    }

}
