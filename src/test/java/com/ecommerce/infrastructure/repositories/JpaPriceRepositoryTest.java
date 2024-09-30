package com.ecommerce.infrastructure.repositories;

import com.ecommerce.domain.entities.Price;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

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
                LocalDateTime.of(2021, 12, 31, 23, 59), 1, (byte) 0, 35.50f, "EUR");

        priceRepository.save(aPrice);

        Price price = priceRepository.findPriceByProductIdAndBrandIdAndDate(
                date,
                35455,
                1
        );

        Assertions.assertEquals(aPrice, price);
    }
}
