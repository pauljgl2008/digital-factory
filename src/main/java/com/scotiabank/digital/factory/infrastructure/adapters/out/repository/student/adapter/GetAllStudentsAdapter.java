package com.scotiabank.digital.factory.infrastructure.adapters.out.repository.student.adapter;

import com.scotiabank.digital.factory.infrastructure.adapters.out.repository.student.StudentCrudRepository;
import com.scotiabank.digital.factory.infrastructure.adapters.out.repository.student.StudentEntityMapper;
import com.scotiabank.digital.factory.domain.model.Student;
import com.scotiabank.digital.factory.domain.ports.out.GetAllStudentsOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class GetAllStudentsAdapter implements GetAllStudentsOutputPort {

    private final StudentCrudRepository studentCrudRepository;

    private final StudentEntityMapper studentEntityMapper;

    @Override
    public Flux<Student> getAll() {
        return studentCrudRepository.findAll()
                .map(studentEntityMapper::toStudent);
    }

}
