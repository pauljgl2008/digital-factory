package com.scotiabank.application.usecase;

import com.scotiabank.domain.model.Status;
import com.scotiabank.domain.model.Student;
import com.scotiabank.domain.ports.out.GetAllStudentsOutputPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("GetAllStudentsUseCase Tests")
public class GetAllStudentsUseCaseTest {

    @InjectMocks
    private GetAllStudentsUseCase getAllStudentsUseCase;

    @Mock
    private GetAllStudentsOutputPort getAllStudentsOutputPort;


    @Test
    void getAll_ReturnsOnlyActiveStudents() {
        Student activeStudent = Student.builder().nombre("John").estado(Status.ACTIVE).build();
        Student inactiveStudent = Student.builder().nombre("Jane").estado(Status.INACTIVE).build();
        when(this.getAllStudentsOutputPort.getAll()).thenReturn(Flux.just(activeStudent, inactiveStudent));

        Flux<Student> result = getAllStudentsUseCase.getAll();

        StepVerifier.create(result)
                .expectNext(activeStudent)
                .verifyComplete();
    }

}
