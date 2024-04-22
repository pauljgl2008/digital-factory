package com.scotiabank.infrastructure.adapters.out.repository.adapter;

import com.scotiabank.domain.model.Student;
import com.scotiabank.domain.ports.out.GetAllStudentsOutputPort;
import com.scotiabank.infrastructure.adapters.out.repository.StudentReactiveCrudRepository;
import com.scotiabank.infrastructure.adapters.out.repository.StudentEntityMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Repository
@RequiredArgsConstructor
public class GetAllStudentsAdapter implements GetAllStudentsOutputPort {

    private final StudentReactiveCrudRepository studentReactiveCrudRepository;

    private final StudentEntityMapper studentEntityMapper;

    @Override
    public Flux<Student> getAll() {
        return studentReactiveCrudRepository.findAll()
                .filter(student -> student.getEstado() != null && student.getEstado())
                .map(studentEntityMapper::toStudent);
    }

}
