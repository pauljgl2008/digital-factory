package com.scotiabank.infrastructure.adapters.in.controller;

import com.scotiabank.domain.ports.in.GetAllStudentsInputPort;
import com.scotiabank.domain.ports.in.InsertStudentInputPort;
import com.scotiabank.infrastructure.adapters.in.controller.dto.StudentRequestDto;
import com.scotiabank.infrastructure.adapters.in.controller.dto.StudentResponseDto;
import com.scotiabank.infrastructure.adapters.in.controller.mapper.StudentDtoMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequestMapping("/api/v1/alumnos")
@RestController
@RequiredArgsConstructor
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
    public Mono<ResponseEntity<Void>> insert(@Valid @RequestBody StudentRequestDto studentRequestDto) {
        return this.insertStudentInputPort.insert(this.studentDtoMapper.toStudent(studentRequestDto))
                .then(Mono.just(ResponseEntity.ok().build()));
    }

}
