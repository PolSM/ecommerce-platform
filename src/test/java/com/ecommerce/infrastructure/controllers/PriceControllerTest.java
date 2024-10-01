package com.ecommerce.infrastructure.controllers;

import com.ecommerce.application.dtos.PriceDTO;
import com.ecommerce.infrastructure.utils.JsonConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class PriceControllerTest {

    private static final int PRODUCT_ID = 35455;
    private static final int BRAND_ID = 1;
    public static final String CURRENCY = "EUR";

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @ParameterizedTest
    @MethodSource("priceProvider")
    public void should_return_OK_given_prices(String dateTime, PriceDTO priceDTO) throws Exception {
        LocalDateTime date = LocalDateTime.parse(dateTime);

        mockMvc.perform(get("/price")
                        .param("product_id", String.valueOf(PRODUCT_ID))
                        .param("brand_id", String.valueOf(BRAND_ID))
                        .param("date", date.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(JsonConverter.convertToJson(priceDTO)));
    }

    @ParameterizedTest
    @MethodSource("priceNotFoundProvider")
    public void should_return_KO_when_not_found(String dateTime) throws Exception {
        LocalDateTime date = LocalDateTime.parse(dateTime);

        mockMvc.perform(get("/price")
                        .param("product_id", String.valueOf(PRODUCT_ID))
                        .param("brand_id", String.valueOf(BRAND_ID))
                        .param("date", date.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Price not found"));
    }

    static Stream<Arguments> priceProvider() {
        return Stream.of(
                Arguments.of("2020-06-14T10:00:00", new PriceDTO(PRODUCT_ID, BRAND_ID, LocalDateTime.parse("2020-06-14T00:00:00"), LocalDateTime.parse("2020-12-31T23:59:59"), 35.50f, CURRENCY)),
                Arguments.of("2020-06-14T16:00:00", new PriceDTO(PRODUCT_ID, BRAND_ID, LocalDateTime.parse("2020-06-14T15:00:00"), LocalDateTime.parse("2020-06-14T18:30:00"), 25.45f, CURRENCY)),
                Arguments.of("2020-06-14T21:00:00", new PriceDTO(PRODUCT_ID, BRAND_ID, LocalDateTime.parse("2020-06-14T00:00:00"), LocalDateTime.parse("2020-12-31T23:59:59"), 35.50f, CURRENCY)),
                Arguments.of("2020-06-15T10:00:00", new PriceDTO(PRODUCT_ID, BRAND_ID, LocalDateTime.parse("2020-06-15T00:00:00"), LocalDateTime.parse("2020-06-15T11:00:00"), 30.50f, CURRENCY)),
                Arguments.of("2020-06-16T21:00:00", new PriceDTO(PRODUCT_ID, BRAND_ID, LocalDateTime.parse("2020-06-15T16:00:00"), LocalDateTime.parse("2020-12-31T23:59:59"), 38.95f, CURRENCY))
        );
    }

    static Stream<Arguments> priceNotFoundProvider() {
        return Stream.of(
                Arguments.of("2019-12-31T23:59:59"),
                Arguments.of("2025-01-01T00:00:00")
        );
    }
}