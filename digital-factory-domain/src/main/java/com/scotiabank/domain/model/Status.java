package com.scotiabank.domain.model;

import com.scotiabank.domain.exception.StudentStatusException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum Status {

    ACTIVE("activo"),

    INACTIVE("inactivo");

    private final String valor;

    public static Status fromValor(String valor) {
        for (Status status : Status.values()) {
            if (status.valor.equalsIgnoreCase(valor)) {
                return status;
            }
        }
        throw new StudentStatusException(HttpStatus.BAD_REQUEST, "estado", valor, "El estado solo puede ser 'activo' o 'inactivo'");
    }
}
