package com.ecommerce.infrastructure.controllers;

import com.ecommerce.application.dtos.PriceDTO;
import com.ecommerce.application.services.PriceService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PriceController.class)
public class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PriceService priceService;

    @Test
    public void should_return_OK_and_data_converted_to_json() throws Exception {
        LocalDateTime dateTime = LocalDateTime.now();
        PriceDTO priceDTO = new PriceDTO(
                35455,
                1,
                LocalDateTime.of(2021, 1, 1, 0, 0),
                LocalDateTime.of(2021, 12, 31, 23, 59),
                35.50f,
                "EUR"
        );
        Mockito.when(priceService.getPrice(1, 1, dateTime)).thenReturn(Optional.of(priceDTO));

        mockMvc.perform(get("/price")
                        .param("product_id", "1")
                        .param("brand_id", "1")
                        .param("date", dateTime.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"productId\":35455,\"brandId\":1,\"startDate\":\"2021-01-01T00:00:00\",\"endDate\":\"2021-12-31T23:59:00\",\"price\":35.50,\"currency\":\"EUR\"}"));
    }

    @Test
    public void should_return_404_and_error_message() throws Exception {
        LocalDateTime dateTime = LocalDateTime.now();

        Mockito.when(priceService.getPrice( 1, 1, dateTime)).thenReturn(Optional.empty());

        mockMvc.perform(get("/price")
                        .param("product_id", "1")
                        .param("brand_id", "1")
                        .param("date", dateTime.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Price not found"));
    }
}