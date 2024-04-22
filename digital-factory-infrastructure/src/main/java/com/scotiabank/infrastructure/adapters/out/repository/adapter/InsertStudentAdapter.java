package com.scotiabank.infrastructure.adapters.out.repository.adapter;

import com.scotiabank.domain.model.Student;
import com.scotiabank.domain.ports.out.InsertStudentOutputPort;
import com.scotiabank.infrastructure.adapters.out.repository.StudentReactiveCrudRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class InsertStudentAdapter implements InsertStudentOutputPort {

    private final StudentReactiveCrudRepository studentReactiveCrudRepository;

    @Override
    public Mono<Void> insert(Student student) {
        return this.studentReactiveCrudRepository.insertStudent(student.getId(), student.getNombre(), student.getApellido(),
                student.getEstado(), student.getEdad());
    }

}
