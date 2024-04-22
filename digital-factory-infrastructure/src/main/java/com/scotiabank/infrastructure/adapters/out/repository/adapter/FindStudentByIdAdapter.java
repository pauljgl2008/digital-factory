package com.scotiabank.infrastructure.adapters.out.repository.adapter;

import com.scotiabank.infrastructure.adapters.out.repository.StudentReactiveCrudRepository;
import com.scotiabank.infrastructure.adapters.out.repository.StudentEntityMapper;
import com.scotiabank.domain.model.Student;
import com.scotiabank.domain.ports.out.FindStudentByIdOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
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
