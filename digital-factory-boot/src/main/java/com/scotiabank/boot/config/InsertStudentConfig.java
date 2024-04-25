package com.scotiabank.boot.config;

import com.scotiabank.application.usecase.InsertStudentUseCase;
import com.scotiabank.infrastructure.adapters.out.repository.adapter.FindStudentByIdAdapter;
import com.scotiabank.infrastructure.adapters.out.repository.adapter.InsertStudentAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración para la inserción de estudiantes.
 */
@Configuration
public class InsertStudentConfig {

    /**
     * Bean para obtener el caso de uso de inserción de estudiantes.
     *
     * @param findStudentByIdAdapter El adaptador para buscar estudiantes por ID.
     * @param insertStudentAdapter   El adaptador para insertar estudiantes.
     * @return El caso de uso para insertar estudiantes.
     */
    @Bean
    public InsertStudentUseCase insertStudentUseCase(FindStudentByIdAdapter findStudentByIdAdapter,
                                                     InsertStudentAdapter insertStudentAdapter) {
        return new InsertStudentUseCase(findStudentByIdAdapter, insertStudentAdapter);
    }

}
