package com.scotiabank.application.usecase;

import com.scotiabank.domain.aggregates.Status;
import com.scotiabank.domain.aggregates.Student;
import com.scotiabank.domain.exception.StudentCreationConflictException;
import com.scotiabank.domain.exception.StudentIdAlreadyExistsException;
import com.scotiabank.domain.ports.out.FindStudentByIdOutputPort;
import com.scotiabank.domain.ports.out.InsertStudentOutputPort;
import org.junit.jupiter.api.BeforeAll;
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

    @InjectMocks
    private InsertStudentUseCase insertStudentUseCase;

    @Mock
    private FindStudentByIdOutputPort findStudentByIdOutputPort;

    @Mock
    private InsertStudentOutputPort insertStudentOutputPort;

    private static Student existingStudent;
    private static Student newStudent;

    @BeforeAll
    static void setUp() {
        existingStudent = createStudent("123", "John", "Doe", Status.INACTIVE, 18);
        newStudent = createStudent("456", "Jane", "Watson", Status.ACTIVE, 20);
    }

    @Test
    void testInsert_givenNoExistingStudent_whenInsertCalled_thenInsertsSuccessfully() {
        when(findStudentByIdOutputPort.findById(newStudent.getId())).thenReturn(Mono.empty());
        when(insertStudentOutputPort.insert(newStudent)).thenReturn(Mono.empty());

        Mono<Void> result = insertStudentUseCase.insert(newStudent);

        StepVerifier.create(result)
                .verifyComplete();
    }

    @Test
    void testInsert_givenExistingStudent_whenInsertCalled_thenThrowsStudentIdAlreadyExistsException() {
        when(findStudentByIdOutputPort.findById(newStudent.getId())).thenReturn(Mono.just(existingStudent));

        Mono<Void> result = insertStudentUseCase.insert(newStudent);

        StepVerifier.create(result)
                .expectError(StudentIdAlreadyExistsException.class)
                .verify();
    }

    @Test
    void testInsert_givenNoExistingStudentButInsertionFails_whenInsertCalled_thenThrowsStudentCreationConflictException() {
        when(findStudentByIdOutputPort.findById(newStudent.getId())).thenReturn(Mono.empty());
        when(insertStudentOutputPort.insert(newStudent)).thenReturn(Mono.error(new StudentCreationConflictException(
                HttpStatus.CONFLICT,
                STUDENT_ID_FIELD, newStudent.getId(),
                STUDENT_INSERTION_CONFLICT_ERROR_MESSAGE)));

        Mono<Void> result = insertStudentUseCase.insert(newStudent);

        StepVerifier.create(result)
                .expectErrorMatches(throwable -> throwable instanceof StudentCreationConflictException &&
                        ((StudentCreationConflictException) throwable).getStatusCode() == HttpStatus.CONFLICT)
                .verify();
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
