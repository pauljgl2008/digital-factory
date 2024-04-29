package com.scotiabank.infrastructure.adapters.in.controller;

import com.scotiabank.domain.aggregates.Status;
import com.scotiabank.domain.aggregates.Student;
import com.scotiabank.domain.ports.in.GetAllStudentsInputPort;
import com.scotiabank.domain.ports.in.InsertStudentInputPort;
import com.scotiabank.infrastructure.adapters.in.controller.dto.StudentRequestDto;
import com.scotiabank.infrastructure.adapters.in.controller.dto.StudentResponseDto;
import com.scotiabank.infrastructure.adapters.in.controller.mapper.StudentDtoMapperImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(MockitoExtension.class)
class StudentControllerTest {

    @InjectMocks
    private StudentController studentController;

    @Mock
    private InsertStudentInputPort insertStudentInputPort;

    @Mock
    private GetAllStudentsInputPort getAllStudentsInputPort;

    @Spy
    private StudentDtoMapperImpl studentDtoMapper;

    private static final List<StudentResponseDto> expectedStudents = new ArrayList<>();

    private static Student student;

    private static StudentRequestDto studentRequestDto;

    @BeforeAll
    static void setUp() throws IOException {
        student = createStudent("1", "John", "Doe", Status.ACTIVE, 28);
        studentRequestDto = createStudentRequestDto("1", "John", "Doe", Status.ACTIVE, 28);
        expectedStudents.add(createStudentResponseDto("1", "John", "Doe", Status.ACTIVE, 28));
    }

    @Test
    void given_requestGetStudents_when_getAllCalled_then_returnsOkAndListOfActiveStudents() {
        Flux<Student> getStudents = Flux.just(student);
        when(getAllStudentsInputPort.getAll()).thenReturn(getStudents);

        Mono<ResponseEntity<Flux<StudentResponseDto>>> result = studentController.getAll();

        assertEquals(HttpStatus.OK, result.block().getStatusCode());
        StepVerifier.create(result.block().getBody())
                .expectNext(expectedStudents.getFirst())
                .verifyComplete();
    }

    @Test
    void given_validStudentRequest_when_insertCalled_then_returnsOkAndStudent() {
        when(studentDtoMapper.toStudent(studentRequestDto)).thenReturn(student);
        when(insertStudentInputPort.insert(student)).thenReturn(Mono.empty());

        Mono<ResponseEntity<Void>> responseMono = studentController.insert(studentRequestDto);

        StepVerifier.create(responseMono)
                .expectNextMatches(response -> response.getStatusCode() == HttpStatus.OK)
                .verifyComplete();
        verify(insertStudentInputPort).insert(student);
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

    private static StudentRequestDto createStudentRequestDto(String id, String name, String lastname, Status status, int age) {
        return StudentRequestDto.builder()
                .id(id)
                .name(name)
                .lastname(lastname)
                .status(status.getValor())
                .age(age)
                .build();
    }

    private static StudentResponseDto createStudentResponseDto(String id, String name, String lastname, Status status, int age) {
        return StudentResponseDto.builder()
                .id(id)
                .name(name)
                .lastname(lastname)
                .status(status.getValor())
                .age(age)
                .build();
    }

}