package com.scotiabank.application.usecase;

import com.scotiabank.domain.exception.StudentCreationConflictException;
import com.scotiabank.domain.exception.StudentIdAlreadyExistsException;
import com.scotiabank.domain.model.Status;
import com.scotiabank.domain.model.Student;
import com.scotiabank.domain.ports.out.FindStudentByIdOutputPort;
import com.scotiabank.domain.ports.out.InsertStudentOutputPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static com.scotiabank.domain.common.ErrorConstants.STUDENT_INSERTION_CONFLICT_ERROR_MESSAGE;
import static com.scotiabank.domain.common.ValidationConstants.STUDENT_ID_FIELD;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InsertStudentUseCaseTest {

    @Mock
    private FindStudentByIdOutputPort findStudentByIdOutputPort;
    @Mock
    private InsertStudentOutputPort insertStudentOutputPort;
    @InjectMocks
    private InsertStudentUseCase insertStudentUseCase;

    @Test
    void insert_WhenStudentDoesNotExist_InsertsSuccessfully() {
        Student student = Student.builder().id("1").nombre("John").apellido("Doe").estado(Status.ACTIVE).edad(18).build();
        when(findStudentByIdOutputPort.findById(student.getId())).thenReturn(Mono.empty());
        when(insertStudentOutputPort.insert(student)).thenReturn(Mono.empty());

        Mono<Void> result = insertStudentUseCase.insert(student);

        StepVerifier.create(result)
                .verifyComplete();
    }

    @Test
    void insert_WhenStudentExists_ThrowsException() {
        Student student = Student.builder().id("123").nombre("John").build();
        Mono<Student> x = Mono.just(student);
        when(findStudentByIdOutputPort.findById(student.getId())).thenReturn(x);

        Mono<Void> result = insertStudentUseCase.insert(student);

        StepVerifier.create(result)
                .expectError(StudentIdAlreadyExistsException.class)
                .verify();
    }

    @Test
    void insert_WhenInsertionFails_ThrowsException() {
        Student student = Student.builder().id("123").nombre("John").build();
        when(findStudentByIdOutputPort.findById(student.getId())).thenReturn(Mono.empty());
        when(insertStudentOutputPort.insert(student)).thenReturn(Mono.error(new StudentCreationConflictException(
                HttpStatus.CONFLICT,
                STUDENT_ID_FIELD, student.getId(),
                STUDENT_INSERTION_CONFLICT_ERROR_MESSAGE)));

        Mono<Void> result = insertStudentUseCase.insert(student);

        StepVerifier.create(result)
                .expectErrorMatches(throwable -> throwable instanceof StudentCreationConflictException &&
                        ((StudentCreationConflictException) throwable).getStatusCode() == HttpStatus.CONFLICT)
                .verify();
    }
}
