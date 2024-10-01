package com.ecommerce.infrastructure.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JsonConverter {

    private static final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    public static String convertToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            throw new JsonProcessingException("Object is null") {};
        }
        return objectMapper.writeValueAsString(object);
    }
}