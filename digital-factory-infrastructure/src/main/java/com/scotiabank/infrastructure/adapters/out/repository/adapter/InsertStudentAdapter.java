package com.scotiabank.infrastructure.adapters.out.repository.adapter;

import com.scotiabank.domain.model.Student;
import com.scotiabank.domain.ports.out.InsertStudentOutputPort;
import com.scotiabank.infrastructure.adapters.out.repository.StudentReactiveCrudRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Repository
public class InsertStudentAdapter implements InsertStudentOutputPort {

    private final StudentReactiveCrudRepository studentReactiveCrudRepository;

    @Override
    public Mono<Void> insert(Student student) {
        return this.studentReactiveCrudRepository.insertStudent(student.getId(), student.getNombre(), student.getApellido(),
                student.getEstado().getValor(), student.getEdad());
    }

}
