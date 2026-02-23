package com.scotiabank.infrastructure.adapters.out.repository.adapter;

import com.scotiabank.domain.aggregates.Student;
import com.scotiabank.domain.ports.out.FindStudentByIdOutputPort;
import com.scotiabank.infrastructure.adapters.out.repository.mapper.StudentEntityMapper;
import com.scotiabank.infrastructure.adapters.out.repository.StudentReactiveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class FindStudentByIdAdapter implements FindStudentByIdOutputPort {

    private final StudentReactiveRepository studentReactiveRepository;

    private final StudentEntityMapper studentEntityMapper;

    @Override
    public Mono<Student> findById(String id) {
        return studentReactiveRepository.findById(id)
                .map(studentEntityMapper::toStudent);
    }

}
