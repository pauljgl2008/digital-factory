package com.scotiabank.domain.aggregates;

import com.scotiabank.domain.exception.StudentStatusException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import static com.scotiabank.domain.common.ValidationConstants.INVALID_STUDENT_STATUS_MESSAGE;
import static com.scotiabank.domain.common.ValidationConstants.STUDENT_STATUS_FIELD;

@AllArgsConstructor
@Getter
public enum Status {

    ACTIVE("activo"),
    INACTIVE("inactivo");

    private static final Map<String, Status> statusMap = Arrays.stream(Status.values())
            .collect(Collectors.toUnmodifiableMap(s -> s.valor, s -> s));

    private final String valor;

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
