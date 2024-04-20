package com.scotiabank.digital.factory.application.usecase;

import com.scotiabank.digital.factory.domain.model.Student;
import com.scotiabank.digital.factory.domain.ports.in.InsertStudentInputPort;
import com.scotiabank.digital.factory.domain.ports.out.FindStudentByIdOutputPort;
import com.scotiabank.digital.factory.domain.ports.out.InsertStudentOutputPort;

public class InsertStudentUseCase implements InsertStudentInputPort {

    private final FindStudentByIdOutputPort findStudentByIdOutputPort;

    private final InsertStudentOutputPort insertStudentOutputPort;

    public InsertStudentUseCase(FindStudentByIdOutputPort findStudentByIdOutputPort,
                                InsertStudentOutputPort insertStudentOutputPort) {
        this.findStudentByIdOutputPort = findStudentByIdOutputPort;
        this.insertStudentOutputPort = insertStudentOutputPort;
    }

    @Override
    public void insert(Student student) {
        this.findStudentByIdOutputPort.findById(student.getId())
                .hasElement()
                .subscribe(studentExist -> {
                    if (studentExist) {
                        System.out.println("Existe el alumno en BD");
                    } else {
                        System.out.println("Insertamos el student");
                        this.insertStudentOutputPort.insert(student);
                    }
                });
    }
}
