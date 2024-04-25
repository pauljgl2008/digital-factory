package com.scotiabank.boot.config;

import com.scotiabank.application.usecase.GetAllStudentsUseCase;
import com.scotiabank.infrastructure.adapters.out.repository.adapter.GetAllStudentsAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración para la obtención de todos los estudiantes.
 */
@Configuration
public class GetAllStudentsConfig {

    /**
     * Bean para obtener el caso de uso de obtener todos los estudiantes.
     *
     * @param getAllStudentsAdapter El adaptador para obtener todos los estudiantes.
     * @return El caso de uso para obtener todos los estudiantes.
     */
    @Bean
    public GetAllStudentsUseCase getAllStudentsUseCase(GetAllStudentsAdapter getAllStudentsAdapter) {
        return new GetAllStudentsUseCase(getAllStudentsAdapter);
    }

}
