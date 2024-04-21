package com.scotiabank.digital.factory.infrastructure.adapters.out.repository.student.adapter;

import com.scotiabank.digital.factory.domain.model.Student;
import com.scotiabank.digital.factory.domain.ports.out.InsertStudentOutputPort;
import com.scotiabank.digital.factory.infrastructure.adapters.out.repository.student.StudentCrudRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
                .then(Mono.just("Estudiante insertado exitosamente")) // Mensaje de éxito
                .onErrorResume(error -> {
                    log.error("Error al insertar el estudiante: " + error.getMessage());
                    return Mono.just("Error al insertar el estudiante: " + error.getMessage()); // Mensaje de error
                });
    }

}
