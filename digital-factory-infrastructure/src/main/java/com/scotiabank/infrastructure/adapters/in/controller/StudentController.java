package com.scotiabank.infrastructure.adapters.in.controller;

import com.scotiabank.domain.exception.DuplicateIdException;
import com.scotiabank.domain.ports.in.GetAllStudentsInputPort;
import com.scotiabank.domain.ports.in.InsertStudentInputPort;
import com.scotiabank.infrastructure.adapters.in.controller.dto.StudentRequestDto;
import com.scotiabank.infrastructure.adapters.in.controller.dto.StudentResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public Mono<ResponseEntity<Void>> insert(@Valid @RequestBody StudentRequestDto studentRequestDto) throws DuplicateIdException {
        return this.insertStudentInputPort.insert(this.studentDtoMapper.toStudent(studentRequestDto))
                .then(Mono.just(ResponseEntity.ok().build()));
    }

}
