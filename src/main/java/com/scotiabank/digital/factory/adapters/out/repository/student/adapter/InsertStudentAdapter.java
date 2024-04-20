package com.scotiabank.digital.factory.adapters.out.repository.student.adapter;

import com.scotiabank.digital.factory.adapters.out.repository.student.StudentCrudRepository;
import com.scotiabank.digital.factory.adapters.out.repository.student.StudentEntityMapper;
import com.scotiabank.digital.factory.adapters.out.repository.student.entity.StudentEntity;
import com.scotiabank.digital.factory.domain.ports.out.InsertStudentOutputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class InsertStudentAdapter implements InsertStudentOutputPort {

    private final StudentCrudRepository studentCrudRepository;

    private final StudentEntityMapper studentEntityMapper;

    @Override
    public void insert(com.scotiabank.digital.factory.domain.model.Student student) {
        StudentEntity studentEntity = studentEntityMapper.toStudentEntity(student);
//        studentCrudRepository.save(studentEntity).subscribe();
        studentCrudRepository.findById(student.getId()).flatMap(studentEntityX -> {
            return studentCrudRepository.save(studentEntity);
        }).switchIfEmpty(Mono.defer(() -> {
            return studentCrudRepository.save(studentEntity);
        }));
    }
}
