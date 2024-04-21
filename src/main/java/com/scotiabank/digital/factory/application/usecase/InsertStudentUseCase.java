package com.scotiabank.digital.factory.application.usecase;

import com.scotiabank.digital.factory.domain.model.Student;
import com.scotiabank.digital.factory.domain.ports.in.InsertStudentInputPort;
import com.scotiabank.digital.factory.domain.ports.out.FindStudentByIdOutputPort;
import com.scotiabank.digital.factory.domain.ports.out.InsertStudentOutputPort;
import lombok.extern.slf4j.Slf4j;

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
    public void insert(Student student) {
        this.findStudentByIdOutputPort.findById(student.getId())
                .hasElement()
                .subscribe(studentExist -> {
                    if (studentExist.equals(true)) {
                        log.debug("Existe el alumno en BD");
                    } else {
                        log.debug("Insertamos el student");
                        this.insertStudentOutputPort.insert(student);
                    }
                });
    }
}
