package com.scotiabank.infrastructure.adapters.out.repository.adapter;

import com.scotiabank.domain.aggregates.Student;
import com.scotiabank.domain.ports.out.InsertStudentOutputPort;
import com.scotiabank.infrastructure.adapters.out.repository.StudentReactiveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class InsertStudentAdapter implements InsertStudentOutputPort {

    private final StudentReactiveRepository studentReactiveRepository;

    @Override
    public Mono<Void> insert(Student student) {
        return this.studentReactiveRepository.insertStudent(student.getId(), student.getName(), student.getLastname(),
                student.getStatus().getValor(), student.getAge());
    }

}
