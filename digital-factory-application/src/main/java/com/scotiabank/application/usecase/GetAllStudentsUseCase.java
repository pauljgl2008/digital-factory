package com.scotiabank.application.usecase;

import com.scotiabank.domain.aggregates.Status;
import com.scotiabank.domain.aggregates.Student;
import com.scotiabank.domain.ports.in.GetAllStudentsInputPort;
import com.scotiabank.domain.ports.out.GetAllStudentsOutputPort;
import reactor.core.publisher.Flux;

/**
 * Caso de uso para obtener todos los alumnos activos.
 */
public class GetAllStudentsUseCase implements GetAllStudentsInputPort {

    private final GetAllStudentsOutputPort getAllStudentsOutputPort;

    /**
     * Constructor del caso de uso.
     *
     * @param getAllStudentsOutputPort Puerto de salida para obtener todos los alumnos.
     */
    public GetAllStudentsUseCase(GetAllStudentsOutputPort getAllStudentsOutputPort) {
        this.getAllStudentsOutputPort = getAllStudentsOutputPort;
    }

    /**
     * Obtiene todos los alumnos activos.
     *
     * @return Un flujo de alumnos activos.
     */
    @Override
    public Flux<Student> getAll() {
        return this.getAllStudentsOutputPort.getAll().filter(this::isStudentActive);
    }

    /**
     * Verifica si un alumno está activo.
     *
     * @param student El alumno a verificar.
     * @return true si el alumno está activo, false en caso contrario.
     */
    private boolean isStudentActive(Student student) {
        return Status.ACTIVE.equals(student.getStatus());
    }

}
