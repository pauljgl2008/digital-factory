package com.scotiabank.application.usecase;

import com.scotiabank.domain.aggregates.Status;
import com.scotiabank.domain.aggregates.Student;
import com.scotiabank.domain.ports.out.GetAllStudentsOutputPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAllStudentsUseCaseTest {

    @InjectMocks
    private GetAllStudentsUseCase getAllStudentsUseCase;

    @Mock
    private GetAllStudentsOutputPort getAllStudentsOutputPort;

    @Test
    void testGetAll_givenStudentsWithMixedStatuses_whenGetAllCalled_thenReturnsOnlyActiveStudents() {
        Student activeStudent = createStudent("456", "Jane", "Watson", Status.ACTIVE, 20);
        Student inactiveStudent = createStudent("123", "John", "Doe", Status.INACTIVE, 18);
        when(this.getAllStudentsOutputPort.getAll()).thenReturn(Flux.just(activeStudent, inactiveStudent));

        Flux<Student> result = getAllStudentsUseCase.getAll();

        StepVerifier.create(result)
                .expectNext(activeStudent)
                .verifyComplete();
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
