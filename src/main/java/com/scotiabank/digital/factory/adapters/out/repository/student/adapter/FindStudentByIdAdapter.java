package com.scotiabank.digital.factory.adapters.out.repository.student.adapter;

import com.scotiabank.digital.factory.adapters.out.repository.student.StudentCrudRepository;
import com.scotiabank.digital.factory.adapters.out.repository.student.StudentEntityMapper;
import com.scotiabank.digital.factory.domain.model.Student;
import com.scotiabank.digital.factory.domain.ports.out.FindStudentByIdOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class FindStudentByIdAdapter implements FindStudentByIdOutputPort {

    private final StudentCrudRepository studentCrudRepository;

    private final StudentEntityMapper studentEntityMapper;

    @Override
    public Mono<Student> findById(String id) {
        return studentCrudRepository.findById(id)
                .map(studentEntityMapper::toStudent);
    }

}
