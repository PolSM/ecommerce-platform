package com.ecommerce.infrastructure.repositories;

import com.ecommerce.builders.PriceBuilder;
import com.ecommerce.domain.entities.Price;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.Optional;

@TestPropertySource("classpath:application-test.properties")
@SpringBootTest
public class JpaPriceRepositoryTest {
    @Autowired
    private JpaPriceRepository priceRepository;
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
        Price aPrice = PriceBuilder.aPrice().build();

        priceRepository.save(aPrice);

        Optional<Price> price = priceRepository.findPriceByProductIdAndBrandIdAndDate(
                PRODUCT_ID,
                BRAND_ID,
                date
        );

        Assertions.assertEquals(Optional.of(aPrice), price);
    }

    @Test
    public void should_not_retrieve_a_price_if_date_before_start_date() {
        Price aPrice = PriceBuilder.aPrice().build();
        priceRepository.save(aPrice);

        Optional<Price> price = priceRepository.findPriceByProductIdAndBrandIdAndDate(
                PRODUCT_ID,
                BRAND_ID,
                LocalDateTime.of(2020, 1, 1, 0, 0)
        );

        Assertions.assertTrue(price.isEmpty());
    }

    @Test
    public void should_not_retrieve_a_price_if_date_after_end_date() {
        Price aPrice = PriceBuilder.aPrice().build();
        priceRepository.save(aPrice);

        Optional<Price> price = priceRepository.findPriceByProductIdAndBrandIdAndDate(
                PRODUCT_ID,
                BRAND_ID,
                LocalDateTime.of(2022, 1, 1, 0, 0)
        );

        Assertions.assertTrue(price.isEmpty());
    }

    @Test
    public void should_retrieve_a_price_by_priority() {
        LocalDateTime date = LocalDateTime.of(2021, 1, 1, 0, 0);
        Price aPrice = PriceBuilder.aPrice().build();
        Price anotherPrice = PriceBuilder.aPrice()
                .setPriority(1)
                .setPrice(25.50f)
                .build();
        priceRepository.save(aPrice);
        priceRepository.save(anotherPrice);

        Optional<Price> price = priceRepository.findPriceByProductIdAndBrandIdAndDate(
                PRODUCT_ID,
                BRAND_ID,
                date
        );

        Assertions.assertEquals(Optional.of(anotherPrice), price);
    }
}