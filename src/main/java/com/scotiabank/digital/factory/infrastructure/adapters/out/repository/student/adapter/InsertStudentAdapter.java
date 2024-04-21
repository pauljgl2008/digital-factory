package com.scotiabank.digital.factory.infrastructure.adapters.out.repository.student.adapter;

import com.scotiabank.digital.factory.infrastructure.adapters.in.controller.student.exception.InvalidFieldException;
import com.scotiabank.digital.factory.domain.model.Student;
import com.scotiabank.digital.factory.domain.ports.out.InsertStudentOutputPort;
import com.scotiabank.digital.factory.infrastructure.adapters.in.controller.student.exception.RepositoryException;
import com.scotiabank.digital.factory.infrastructure.adapters.out.repository.student.StudentCrudRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class InsertStudentAdapter implements InsertStudentOutputPort {

    private final StudentCrudRepository studentCrudRepository;

    @Override
    public Mono<String> insert(Student student) {
        return this.studentCrudRepository
                .insertStudent(student.getId(), student.getNombre(), student.getApellido(), student.getEstado(), student.getEdad())
                .then(Mono.just("Estudiante insertado exitosamente"))
                .onErrorResume(error -> {
                    log.error("RepositoryException insertStudent: " + error.getMessage());
                    throw new RepositoryException(HttpStatus.CONFLICT, "id", student.getId(),
                            error.getMessage());
                });
    }

}
