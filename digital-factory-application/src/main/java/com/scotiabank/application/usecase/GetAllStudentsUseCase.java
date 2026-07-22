package com.scotiabank.application.usecase;

import com.scotiabank.domain.aggregates.Status;
import com.scotiabank.domain.aggregates.Student;
import com.scotiabank.domain.ports.in.GetAllStudentsInputPort;
import com.scotiabank.domain.ports.out.GetAllStudentsOutputPort;
import reactor.core.publisher.Flux;

public class GetAllStudentsUseCase implements GetAllStudentsInputPort {

    private final GetAllStudentsOutputPort getAllStudentsOutputPort;

    public GetAllStudentsUseCase(GetAllStudentsOutputPort getAllStudentsOutputPort) {
        this.getAllStudentsOutputPort = getAllStudentsOutputPort;
    }

    @Override
    public Flux<Student> getAll() {
        return getAllStudentsOutputPort.getAll().filter(this::isActive);
    }

    private boolean isActive(Student student) {
        return Status.ACTIVE.equals(student.getStatus());
    }

}
