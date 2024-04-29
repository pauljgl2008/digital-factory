package com.scotiabank.infrastructure.adapters.out.repository.adapter;

import com.scotiabank.domain.aggregates.Status;
import com.scotiabank.domain.aggregates.Student;
import com.scotiabank.infrastructure.adapters.out.repository.StudentReactiveRepository;
import com.scotiabank.infrastructure.adapters.out.repository.entity.StudentEntity;
import com.scotiabank.infrastructure.adapters.out.repository.mapper.StudentEntityMapperImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindStudentByIdAdapterTest {

    @InjectMocks
    private FindStudentByIdAdapter findStudentByIdAdapter;

    @Mock
    private StudentReactiveRepository studentReactiveRepository;

    @Spy
    private StudentEntityMapperImpl studentEntityMapper;

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
    void testFindById_givenValidIdStudent_whenFindByIdCalled_thenReturnsStudent() {
        when(studentReactiveRepository.findById(studentId)).thenReturn(Mono.just(studentEntity));

        Mono<Student> result = findStudentByIdAdapter.findById(studentId);

        StepVerifier.create(result)
                .expectNext(expectedStudent)
                .verifyComplete();
        verify(studentEntityMapper).toStudent(studentEntity);
    }

    private static StudentEntity createStudentEntity(String id, String name, String lastname, Status status, int age) {
        return StudentEntity.builder()
                .id(id)
                .name(name)
                .lastname(lastname)
                .status(status.getValor())
                .age(age)
                .build();
    }

    private static Student createStudent(String id, String name, String lastname, Status status, int age) {
        return Student.builder()
                .id(id)
                .name(name)
                .lastname(lastname)
                .status(status)
                .age(age)
                .build();
    }

}
