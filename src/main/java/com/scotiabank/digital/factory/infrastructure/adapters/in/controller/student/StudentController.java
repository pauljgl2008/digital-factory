package com.scotiabank.digital.factory.infrastructure.adapters.in.controller.student;


import com.scotiabank.digital.factory.application.usecase.MessageResponseDto;
import com.scotiabank.digital.factory.domain.ports.in.GetAllStudentsInputPort;
import com.scotiabank.digital.factory.domain.ports.in.InsertStudentInputPort;
import com.scotiabank.digital.factory.infrastructure.adapters.in.controller.student.dto.StudentRequestDto;
import com.scotiabank.digital.factory.infrastructure.adapters.in.controller.student.dto.StudentResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/alumnos")
@RequiredArgsConstructor
@Slf4j
public class StudentController {

    private final InsertStudentInputPort insertStudentInputPort;

    private final GetAllStudentsInputPort getAllStudentsInputPort;

    private final StudentDtoMapper studentDtoMapper;

    @GetMapping
    public Mono<ResponseEntity<Flux<StudentResponseDto>>> getAll() {
        return this.getAllStudentsInputPort.getAll()
                .map(studentDtoMapper::toStudentResponse)
                .flatMap(Mono::just)
                .collectList()
                .map(studentResponses -> ResponseEntity.ok()
                        .body(Flux.fromIterable(studentResponses)));
    }

    @PostMapping
    public Mono<ResponseEntity<MessageResponseDto>> insert(@Valid @RequestBody StudentRequestDto studentRequestDto) {
        log.info(studentRequestDto.toString());
        return this.insertStudentInputPort.insert(this.studentDtoMapper.toStudent(studentRequestDto))
                .map(message -> ResponseEntity.ok(new MessageResponseDto(message)))
                .onErrorResume(error -> {
                    log.error("Error al insertar un estudiante: " + error.getMessage());
                    return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponseDto("Error al insertar un estudiante")));
                });
    }
}
