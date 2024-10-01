package com.ecommerce.application.services;

import com.ecommerce.application.dtos.PriceDTO;
import com.ecommerce.builders.PriceBuilder;
import com.ecommerce.domain.entities.Price;
import com.ecommerce.infrastructure.repositories.BaseJpaPriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PriceServiceTest {

    @Mock
    private BaseJpaPriceRepository priceRepository;

    @InjectMocks
    private PriceService priceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private static final int PRODUCT_ID = 35455;
    private static final int BRAND_ID = 1;
    private static final LocalDateTime DATE = LocalDateTime.now();

    @Test
    void should_retrieve_a_price_and_serialize_it() {
        Price price = PriceBuilder.aPrice().build();
        when(priceRepository.findPriceByProductIdAndBrandIdAndDate(DATE, PRODUCT_ID, BRAND_ID)).thenReturn(Optional.ofNullable(price));

        Optional<PriceDTO> priceDTO = priceService.getPrice(DATE, PRODUCT_ID, BRAND_ID);

        PriceDTO expectedDto = new PriceDTO(
                PRODUCT_ID,
                BRAND_ID,
                LocalDateTime.of(2021, 1, 1, 0, 0),
                LocalDateTime.of(2021, 12, 31, 23, 59),
                35.50f,
                "EUR"
        );
        assertEquals(expectedDto, priceDTO.get());
        verify(priceRepository, times(1)).findPriceByProductIdAndBrandIdAndDate(DATE, PRODUCT_ID, BRAND_ID);
    }

    @Test
    void should_return_null_if_there_is_no_price() {
        LocalDateTime date = LocalDateTime.now();

        when(priceRepository.findPriceByProductIdAndBrandIdAndDate(date, PRODUCT_ID, BRAND_ID)).thenReturn(Optional.empty());

        Optional<PriceDTO> priceDTO = priceService.getPrice(date, PRODUCT_ID, BRAND_ID);

        assertTrue(priceDTO.isEmpty());
    }
}