package com.scotiabank.domain.model;

import com.scotiabank.domain.exception.StudentStatusException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static com.scotiabank.domain.common.ValidationConstants.INVALID_STUDENT_STATUS_MESSAGE;
import static com.scotiabank.domain.common.ValidationConstants.STUDENT_STATUS_FIELD;

@AllArgsConstructor
@Getter
public enum Status {

    ACTIVE("activo"),

    INACTIVE("inactivo");

    private final String valor;

    public static Status fromValor(String valor) {
        for (Status status : Status.values()) {
            if (status.valor.equals(valor)) {
                return status;
            }
        }
        throw new StudentStatusException(HttpStatus.BAD_REQUEST, STUDENT_STATUS_FIELD, valor,
                INVALID_STUDENT_STATUS_MESSAGE);
    }

}
