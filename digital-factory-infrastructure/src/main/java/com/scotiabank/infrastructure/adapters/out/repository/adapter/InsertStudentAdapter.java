package com.scotiabank.infrastructure.adapters.out.repository.adapter;

import com.scotiabank.domain.model.Student;
import com.scotiabank.domain.ports.out.InsertStudentOutputPort;
import com.scotiabank.infrastructure.adapters.out.repository.StudentReactiveCrudRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class InsertStudentAdapter implements InsertStudentOutputPort {

    private final StudentReactiveCrudRepository studentReactiveCrudRepository;

    @Override
    public Mono<Void> insert(Student student) {
        return this.studentReactiveCrudRepository.insertStudent(student.getId(), student.getNombre(), student.getApellido(),
                student.getEstado().getValor(), student.getEdad());
    }

}
