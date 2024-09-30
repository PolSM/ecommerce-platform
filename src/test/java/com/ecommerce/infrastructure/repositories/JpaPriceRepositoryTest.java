package com.ecommerce.infrastructure.repositories;

import com.ecommerce.domain.entities.Price;
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

    @Test
    public void should_retrieve_a_price() {
        LocalDateTime date = LocalDateTime.of(2021, 1, 1, 0, 0);
        Price aPrice = new Price(35455, 1, date,
                LocalDateTime.of(2021, 12, 31, 23, 59), 1, 0, 35.50f, "EUR");

        priceRepository.save(aPrice);

        Price price = priceRepository.findPriceByProductIdAndBrandIdAndDate(
                date,
                35455,
                1
        );

        Assertions.assertEquals(aPrice, price);
    }

    @Test
    public void should_not_retrieve_a_price_if_date_before_start_date() {
        Price aPrice = new Price(35455, 1, LocalDateTime.of(2021, 1, 1, 0, 0),
                LocalDateTime.of(2021, 12, 31, 23, 59), 1, 0, 35.50f, "EUR");
        priceRepository.save(aPrice);

        Price price = priceRepository.findPriceByProductIdAndBrandIdAndDate(
                LocalDateTime.of(2020, 1, 1, 0, 0),
                35455,
                1
        );

        Assertions.assertNull(price);
    }

    @Test
    public void should_not_retrieve_a_price_if_date_after_end_date() {
        Price aPrice = new Price(35455, 1, LocalDateTime.of(2021, 1, 1, 0, 0),
                LocalDateTime.of(2021, 12, 31, 23, 59), 1, 0, 35.50f, "EUR");
        priceRepository.save(aPrice);

        Price price = priceRepository.findPriceByProductIdAndBrandIdAndDate(
                LocalDateTime.of(2022, 1, 1, 0, 0),
                35455,
                1
        );

        Assertions.assertNull(price);
    }

    @Test
    public void should_retrieve_a_price_by_priority() {
        LocalDateTime startDate = LocalDateTime.of(2021, 1, 1, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2021, 12, 31, 23, 59);
        Price aPrice = new Price(35455, 1, startDate,
                endDate, 1, 0, 35.50f, "EUR");
        Price anotherPrice = new Price(35455, 1, startDate,
                endDate, 2, 1, 25.50f, "EUR");
        priceRepository.save(aPrice);
        priceRepository.save(anotherPrice);

        Price price = priceRepository.findPriceByProductIdAndBrandIdAndDate(
                startDate,
                35455,
                1
        );

        Assertions.assertEquals(anotherPrice, price);
    }
}