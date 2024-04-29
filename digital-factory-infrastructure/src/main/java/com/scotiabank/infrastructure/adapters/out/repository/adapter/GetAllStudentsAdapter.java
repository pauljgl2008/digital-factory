package com.scotiabank.infrastructure.adapters.out.repository.adapter;

import com.scotiabank.domain.aggregates.Student;
import com.scotiabank.domain.ports.out.GetAllStudentsOutputPort;
import com.scotiabank.infrastructure.adapters.out.repository.StudentReactiveRepository;
import com.scotiabank.infrastructure.adapters.out.repository.mapper.StudentEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
@RequiredArgsConstructor
public class GetAllStudentsAdapter implements GetAllStudentsOutputPort {

    private final StudentReactiveRepository studentReactiveRepository;

    private final StudentEntityMapper studentEntityMapper;

    @Override
    public Flux<Student> getAll() {
        return studentReactiveRepository.findAll()
                .map(studentEntityMapper::toStudent);
    }

}
