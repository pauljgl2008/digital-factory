package com.scotiabank.infrastructure.adapters.in.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.scotiabank.domain.model.Status;
import com.scotiabank.domain.model.Student;
import com.scotiabank.domain.ports.in.GetAllStudentsInputPort;
import com.scotiabank.domain.ports.in.InsertStudentInputPort;
import com.scotiabank.infrastructure.adapters.in.controller.common.TestUtil;
import com.scotiabank.infrastructure.adapters.in.controller.dto.StudentRequestDto;
import com.scotiabank.infrastructure.adapters.in.controller.dto.StudentResponseDto;
import com.scotiabank.infrastructure.adapters.in.controller.mapper.StudentDtoMapper;
import com.scotiabank.infrastructure.adapters.in.controller.mapper.StudentDtoMapperImpl;
import org.junit.jupiter.api.Assertions;
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
import java.util.List;
import java.util.Objects;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StudentControllerTest {

    @InjectMocks
    private StudentController studentController;

    @Mock
    private InsertStudentInputPort insertStudentInputPort;

    @Mock
    private GetAllStudentsInputPort getAllStudentsInputPort;

    @Spy
    private StudentDtoMapperImpl studentDtoMapper;

    private static List<StudentResponseDto> expectedStudents;

    private static Student student;
    private static StudentRequestDto studentRequestDto;

    private static final String GET_STUDENTS_RESPONSE_JSON_PATH = "src/test/resources/GetStudentsResponse.json";

    @BeforeAll
    static void setUp() throws IOException {
        student = createStudent("1", "John", "Doe", Status.ACTIVE, 28);
        studentRequestDto = createStudentRequestDto("1", "John", "Doe", Status.ACTIVE, 28);
        expectedStudents = TestUtil.convertJsonToDto(GET_STUDENTS_RESPONSE_JSON_PATH, new TypeReference<>() {
        });
    }

    @Test
    void shouldReturnEmptyListWhenNoStudentsExist() {
        when(getAllStudentsInputPort.getAll()).thenReturn(Flux.empty());

        Mono<ResponseEntity<Flux<StudentResponseDto>>> result = studentController.getAll();

        StepVerifier.create(result)
                .expectNextMatches(response -> response.getStatusCode() == HttpStatus.OK)
                .expectNextCount(0)
                .verifyComplete();
    }

    @Test
    void testGetAllStudents_ShouldReturnListOfStudents() {
        Student activeStudent = Student.builder().id("1").nombre("John").apellido("Doe").estado(Status.ACTIVE)
                .edad(28).build();
        Flux<Student> getStudents = Flux.just(activeStudent);
        when(getAllStudentsInputPort.getAll()).thenReturn(getStudents);

        Mono<ResponseEntity<Flux<StudentResponseDto>>> result = studentController.getAll();

        List<StudentResponseDto> expected = expectedStudents;

        ResponseEntity<Flux<StudentResponseDto>> current = result.block();
        Assertions.assertEquals(HttpStatus.OK, result.block().getStatusCode());
        StepVerifier.create(current.getBody())
                .expectNext(expected.get(0))
                .verifyComplete();
    }

    @Test
    void shouldInsertStudentAndReturnSuccess() {

        when(studentDtoMapper.toStudent(studentRequestDto)).thenReturn(student);
        when(insertStudentInputPort.insert(student)).thenReturn(Mono.empty());

        Mono<ResponseEntity<Void>> responseMono = studentController.insert(studentRequestDto);

        StepVerifier.create(responseMono)
                .expectNextMatches(response -> response.getStatusCode() == HttpStatus.OK)
                .verifyComplete();

        verify(insertStudentInputPort).insert(student);
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

    private static StudentRequestDto createStudentRequestDto(String id, String nombre, String apellido, Status estado, int edad) {
        return StudentRequestDto.builder()
                .id(id)
                .nombre(nombre)
                .apellido(apellido)
                .estado(estado.getValor())
                .edad(edad)
                .build();

    }
}