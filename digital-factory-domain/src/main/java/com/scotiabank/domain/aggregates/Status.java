package com.scotiabank.domain.aggregates;

import com.scotiabank.domain.exception.StudentStatusException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

import static com.scotiabank.domain.common.ValidationConstants.INVALID_STUDENT_STATUS_MESSAGE;
import static com.scotiabank.domain.common.ValidationConstants.STUDENT_STATUS_FIELD;

/**
 * Enumeración que representa el estado de un alumno.
 */
@AllArgsConstructor
@Getter
public enum Status {

    /**
     * Estado activo.
     */
    ACTIVE("activo"),

    /**
     * Estado inactivo.
     */
    INACTIVE("inactivo");

    /**
     * Mapa para mapear los valores de estado a los enumerados Status.
     */
    private static final Map<String, Status> statusMap = new HashMap<>();

    static {
        for (Status status : Status.values()) {
            statusMap.put(status.valor, status);
        }
    }

    /**
     * Valor asociado al estado.
     */
    private final String valor;

    /**
     * Devuelve el Status correspondiente al valor dado.
     *
     * @param valor El valor del estado.
     * @return El Status correspondiente al valor dado.
     * @throws StudentStatusException Si no se encuentra un Status correspondiente al valor dado.
     */
    public static Status fromValor(String valor) {
        Status status = statusMap.get(valor);
        if (status != null) {
            return status;
        }
        throw new StudentStatusException(HttpStatus.BAD_REQUEST,
                STUDENT_STATUS_FIELD, valor,
                INVALID_STUDENT_STATUS_MESSAGE);
    }
}
