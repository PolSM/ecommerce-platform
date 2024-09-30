package com.ecommerce.infrastructure.repositories;

import com.ecommerce.domain.entities.Price;
import com.ecommerce.builders.PriceBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;

@DataJpaTest
public class JpaPriceRepositoryTest {
    @Autowired
    private BaseJpaPriceRepository priceRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void setUp() {
        jdbcTemplate.execute("TRUNCATE TABLE ecommerce_platform.tbl_prices");
    }

    private static final int PRODUCT_ID = 35455;
    private static final int BRAND_ID = 1;

    @Test
    public void should_retrieve_a_price() {
        LocalDateTime date = LocalDateTime.of(2021, 1, 1, 0, 0);
        Price aPrice = PriceBuilder.aPrice();

        priceRepository.save(aPrice);

        Price price = priceRepository.findPriceByProductIdAndBrandIdAndDate(
                date,
                PRODUCT_ID,
                BRAND_ID
        );

        Assertions.assertEquals(aPrice, price);
    }

    @Test
    public void should_not_retrieve_a_price_if_date_before_start_date() {
        Price aPrice = PriceBuilder.aPrice();
        priceRepository.save(aPrice);

        Price price = priceRepository.findPriceByProductIdAndBrandIdAndDate(
                LocalDateTime.of(2020, 1, 1, 0, 0),
                PRODUCT_ID,
                BRAND_ID
        );

        Assertions.assertNull(price);
    }

    @Test
    public void should_not_retrieve_a_price_if_date_after_end_date() {
        Price aPrice = PriceBuilder.aPrice();
        priceRepository.save(aPrice);

        Price price = priceRepository.findPriceByProductIdAndBrandIdAndDate(
                LocalDateTime.of(2022, 1, 1, 0, 0),
                PRODUCT_ID,
                BRAND_ID
        );

        Assertions.assertNull(price);
    }

    @Test
    public void should_retrieve_a_price_by_priority() {
        LocalDateTime date = LocalDateTime.of(2021, 1, 1, 0, 0);
        Price aPrice = PriceBuilder.aPrice();
        Price anotherPrice = new PriceBuilder()
                .setPriority(1)
                .setPrice(25.50f)
                .build();
        priceRepository.save(aPrice);
        priceRepository.save(anotherPrice);

        Price price = priceRepository.findPriceByProductIdAndBrandIdAndDate(
                date,
                PRODUCT_ID,
                BRAND_ID
        );

        Assertions.assertEquals(anotherPrice, price);
    }
}