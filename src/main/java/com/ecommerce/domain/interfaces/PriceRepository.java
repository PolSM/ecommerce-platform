package com.ecommerce.domain.interfaces;

import com.ecommerce.domain.entities.Price;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface PriceRepository {
    Optional<Price> findPriceByProductIdAndBrandIdAndDate(Integer productId, Integer brandId, LocalDateTime date);
    void save(Price price);
}
