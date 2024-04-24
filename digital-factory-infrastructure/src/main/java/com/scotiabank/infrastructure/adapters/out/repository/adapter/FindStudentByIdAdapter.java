package com.scotiabank.infrastructure.adapters.out.repository.adapter;

import com.scotiabank.domain.model.Student;
import com.scotiabank.domain.ports.out.FindStudentByIdOutputPort;
import com.scotiabank.infrastructure.adapters.out.repository.StudentEntityMapper;
import com.scotiabank.infrastructure.adapters.out.repository.StudentReactiveCrudRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;


@Repository
@RequiredArgsConstructor
public class FindStudentByIdAdapter implements FindStudentByIdOutputPort {

    private final StudentReactiveCrudRepository studentReactiveCrudRepository;

    private final StudentEntityMapper studentEntityMapper;

    @Override
    public Mono<Student> findById(String id) {
        return studentReactiveCrudRepository.findById(id)
                .map(studentEntityMapper::toStudent);
    }

}
