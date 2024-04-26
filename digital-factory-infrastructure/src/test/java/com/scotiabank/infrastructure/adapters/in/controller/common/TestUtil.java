package com.scotiabank.infrastructure.adapters.in.controller.common;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import reactor.core.publisher.Flux;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class TestUtil {
    private final static ObjectMapper OBJECT_MAPPER;

    static {
        OBJECT_MAPPER = new ObjectMapper();
    }

    public static <T> Flux<T> convertJsonToFlux(String filePath, Class<T> elementType) throws IOException {
        File file = new File(filePath);
        List<T> list = OBJECT_MAPPER.readValue(file, new TypeReference<List<T>>() {
        });
        return Flux.fromIterable(list);
    }
    public static <T> T convertJsonToDto(String filePath, TypeReference<T> typeReference) throws IOException {
        File file = new File(filePath);
        return OBJECT_MAPPER.readValue(file, typeReference);
    }

    public static <T> T convertJsonToDto(String filePath, Class<T> valueType) throws IOException {
        File file = new File(filePath);
        return OBJECT_MAPPER.readValue(file, valueType);
    }
}
