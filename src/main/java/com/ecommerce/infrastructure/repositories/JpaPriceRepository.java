package com.ecommerce.infrastructure.repositories;

import com.ecommerce.domain.entities.Price;
import com.ecommerce.domain.ports.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaPriceRepository implements PriceRepository {

    private final BaseJpaPriceRepository base;

    @Autowired
    public JpaPriceRepository(BaseJpaPriceRepository base) {
        this.base = base;
    }

    @Override
    public Optional<Price> findPriceByProductIdAndBrandIdAndDate(Integer productId, Integer brandId, LocalDateTime date) {
        Optional<List<Price>> prices = base.findPricesByProductIdAndBrandIdAndDate(productId, brandId, date);
        return prices.flatMap(priceList -> priceList.stream()
                .max(Comparator.comparingInt(Price::priority)));
    }

    @Override
    public void save(Price price) {
        base.save(price);
    }
}