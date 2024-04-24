package com.scotiabank.application.usecase;

import com.scotiabank.domain.model.Student;
import com.scotiabank.domain.ports.in.GetAllStudentsInputPort;
import com.scotiabank.domain.ports.out.GetAllStudentsOutputPort;
import reactor.core.publisher.Flux;

public class GetAllStudentsUseCaseImpl implements GetAllStudentsInputPort {

    private final GetAllStudentsOutputPort getAllStudentsOutputPort;

    public GetAllStudentsUseCaseImpl(GetAllStudentsOutputPort getAllStudentsOutputPort) {
        this.getAllStudentsOutputPort = getAllStudentsOutputPort;
    }

    @Override
    public Flux<Student> getAll() {
        return this.getAllStudentsOutputPort.getAll();
    }

}
