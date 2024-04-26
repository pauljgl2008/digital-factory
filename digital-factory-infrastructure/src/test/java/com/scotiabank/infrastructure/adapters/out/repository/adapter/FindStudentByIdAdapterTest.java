package com.scotiabank.infrastructure.adapters.out.repository.adapter;

import com.scotiabank.domain.aggregates.Status;
import com.scotiabank.domain.aggregates.Student;
import com.scotiabank.infrastructure.adapters.out.repository.StudentReactiveCrudRepository;
import com.scotiabank.infrastructure.adapters.out.repository.entity.StudentEntity;
import com.scotiabank.infrastructure.adapters.out.repository.mapper.StudentEntityMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindStudentByIdAdapterTest {

    @InjectMocks
    private FindStudentByIdAdapter findStudentByIdAdapter;

    @Mock
    private StudentReactiveCrudRepository studentReactiveCrudRepository;

    @Mock
    private StudentEntityMapper studentEntityMapper;

    private static String studentId;

    private static StudentEntity studentEntity;

    private static Student expectedStudent;

    @BeforeAll
    static void setUp() {
        studentId = "1";
        studentEntity = createStudentEntity(studentId, "John", "Doe", Status.ACTIVE, 28);
        expectedStudent = createStudent(studentId, "John", "Doe", Status.ACTIVE, 28);
    }

    @Test
    void given_validIdStudent_when_findByIdCalled_then_returnsStudent() {
        when(studentReactiveCrudRepository.findById(studentId)).thenReturn(Mono.just(studentEntity));
        when(studentEntityMapper.toStudent(any(StudentEntity.class))).thenReturn(expectedStudent);

        Mono<Student> result = findStudentByIdAdapter.findById(studentId);

        StepVerifier.create(result)
                .expectNext(expectedStudent)
                .verifyComplete();
        verify(studentEntityMapper).toStudent(studentEntity);
    }

    private static StudentEntity createStudentEntity(String id, String nombre, String apellido, Status estado, int edad) {
        return StudentEntity.builder()
                .id(id)
                .nombre(nombre)
                .apellido(apellido)
                .estado(estado.getValor())
                .edad(edad)
                .build();
    }

    private static Student createStudent(String id, String nombre, String apellido, Status estado, int edad) {
        return Student.builder()
                .id(id)
                .nombre(nombre)
                .apellido(apellido)
                .estado(estado)
                .edad(edad)
                .build();
    }

}
