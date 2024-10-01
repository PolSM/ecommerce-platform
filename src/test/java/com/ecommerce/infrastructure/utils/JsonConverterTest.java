package com.ecommerce.infrastructure.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonConverterTest {
    @Test
    void should_convert_an_object_into_a_JSON() {
        TestObject testObject = new TestObject("test", 123);
        String json = "";
        try {
            json = JsonConverter.convertToJson(testObject);
        } catch (Exception e) {
            fail("Exception should not be thrown");
        }
        assertEquals("{\"name\":\"test\",\"value\":123}", json);
    }

    @Test
    void should_throw_exception_when_object_is_null() {
        assertThrows(JsonProcessingException.class, () -> JsonConverter.convertToJson(null));
    }


    private static class TestObject {
        private String name;
        private int value;

        public TestObject(String name, int value) {
            this.name = name;
            this.value = value;
        }

        public TestObject(){}

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}