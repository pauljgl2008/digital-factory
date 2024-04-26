package com.scotiabank.application.usecase;

import com.scotiabank.domain.exception.StudentCreationConflictException;
import com.scotiabank.domain.exception.StudentIdAlreadyExistsException;
import com.scotiabank.domain.aggregates.Student;
import com.scotiabank.domain.ports.in.InsertStudentInputPort;
import com.scotiabank.domain.ports.out.FindStudentByIdOutputPort;
import com.scotiabank.domain.ports.out.InsertStudentOutputPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Mono;

import static com.scotiabank.domain.common.ErrorConstants.STUDENT_INSERTION_CONFLICT_ERROR_MESSAGE;
import static com.scotiabank.domain.common.ErrorConstants.STUDENT_INSERTION_ERROR_FORMAT;
import static com.scotiabank.domain.common.ValidationConstants.STUDENT_ID_ALREADY_EXISTS_MESSAGE;
import static com.scotiabank.domain.common.ValidationConstants.STUDENT_ID_FIELD;

/**
 * Caso de uso para insertar un alumno.
 */
@Slf4j
public class InsertStudentUseCase implements InsertStudentInputPort {

    private final FindStudentByIdOutputPort findStudentByIdOutputPort;
    private final InsertStudentOutputPort insertStudentOutputPort;

    /**
     * Constructor de la clase.
     *
     * @param findStudentByIdOutputPort Puerto de salida para buscar un alumno por ID.
     * @param insertStudentOutputPort   Puerto de salida para insertar un alumno.
     */
    public InsertStudentUseCase(FindStudentByIdOutputPort findStudentByIdOutputPort,
                                InsertStudentOutputPort insertStudentOutputPort) {
        this.findStudentByIdOutputPort = findStudentByIdOutputPort;
        this.insertStudentOutputPort = insertStudentOutputPort;
    }

    /**
     * Inserta un alumno.
     *
     * @param student El alumno a insertar.
     * @return Un Mono<Void> que indica la finalización de la operación de inserción.
     */
    @Override
    public Mono<Void> insert(Student student) {
        return findStudentByIdOutputPort.findById(student.getId())
                .hasElement()
                .flatMap(studentExists -> Boolean.TRUE.equals(studentExists)
                        ? handleStudentExists(student)
                        : insertStudentOutputPort.insert(student)
                        .onErrorResume(error -> handleInsertionError(error, student.getId())));
    }

    /**
     * Maneja el caso cuando el alumno ya existe.
     *
     * @param student El alumno que ya existe.
     * @return Un Mono<Void> que indica el manejo del caso de alumno existente.
     */
    private Mono<Void> handleStudentExists(Student student) {
        return Mono.error(new StudentIdAlreadyExistsException(HttpStatus.BAD_REQUEST,
                STUDENT_ID_FIELD, student.getId(),
                STUDENT_ID_ALREADY_EXISTS_MESSAGE));
    }

    /**
     * Maneja el error durante la inserción de un alumno.
     *
     * @param error     El error que ocurrió durante la inserción.
     * @param studentId El ID del alumno que se estaba intentando insertar.
     * @return Un Mono<Void> que indica el manejo del error de inserción.
     */
    private Mono<Void> handleInsertionError(Throwable error, String studentId) {
        log.error(String.format(STUDENT_INSERTION_ERROR_FORMAT, studentId, error.getMessage()));
        return Mono.error(new StudentCreationConflictException(HttpStatus.CONFLICT,
                STUDENT_ID_FIELD, studentId,
                STUDENT_INSERTION_CONFLICT_ERROR_MESSAGE));
    }

}
