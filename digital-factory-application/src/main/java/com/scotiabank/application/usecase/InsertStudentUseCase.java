package com.scotiabank.application.usecase;

import com.scotiabank.domain.exception.ErrorConstants;
import com.scotiabank.domain.exception.StudentCreationConflictException;
import com.scotiabank.domain.exception.StudentIdAlreadyExistsException;
import com.scotiabank.domain.exception.ValidationConstants;
import com.scotiabank.domain.model.Student;
import com.scotiabank.domain.ports.in.InsertStudentInputPort;
import com.scotiabank.domain.ports.out.FindStudentByIdOutputPort;
import com.scotiabank.domain.ports.out.InsertStudentOutputPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Mono;

@Slf4j
public class InsertStudentUseCase implements InsertStudentInputPort {
    private final FindStudentByIdOutputPort findStudentByIdOutputPort;

    private final InsertStudentOutputPort insertStudentOutputPort;

    public InsertStudentUseCase(FindStudentByIdOutputPort findStudentByIdOutputPort,
                                InsertStudentOutputPort insertStudentOutputPort) {
        this.findStudentByIdOutputPort = findStudentByIdOutputPort;
        this.insertStudentOutputPort = insertStudentOutputPort;
    }

    @Override
    public Mono<Void> insert(Student student) {
        return this.findStudentByIdOutputPort.findById(student.getId())
                .hasElement()
                .flatMap(studentExist -> {
                    if (studentExist.equals(true)) {
                        return Mono.error(new StudentIdAlreadyExistsException(HttpStatus.BAD_REQUEST,
                                ValidationConstants.STUDENT_ID_FIELD, student.getId(),
                                ValidationConstants.STUDENT_ID_ALREADY_EXISTS_MESSAGE));
                    } else {
                        return this.insertStudentOutputPort.insert(student)
                                .onErrorResume(error -> {
                                    log.error(String.format(ErrorConstants.STUDENT_INSERTION_ERROR_FORMAT,
                                            student.getId(), error.getMessage()));
                                    return Mono.error(new StudentCreationConflictException(HttpStatus.CONFLICT,
                                            ValidationConstants.STUDENT_ID_FIELD, student.getId(),
                                            ErrorConstants.STUDENT_INSERTION_ERROR_MESSAGE
                                    ));
                                });
                    }
                });
    }
}
