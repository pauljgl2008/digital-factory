package com.scotiabank.digital.factory.infrastructure.adapters.out.repository.student.adapter;

import com.scotiabank.digital.factory.domain.model.Student;
import com.scotiabank.digital.factory.domain.ports.out.InsertStudentOutputPort;
import com.scotiabank.digital.factory.infrastructure.adapters.out.repository.student.StudentCrudRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class InsertStudentAdapter implements InsertStudentOutputPort {

    private final StudentCrudRepository studentCrudRepository;

    @Override
    public void insert(Student student) {
        this.studentCrudRepository
                .insertStudent(student.getId(), student.getNombre(), student.getApellido(), student.getEstado(),
                        student.getEdad())
                .doOnError(error -> log.error("Error inserting student (message): " + error.getMessage()))
                .subscribe();
    }

}
