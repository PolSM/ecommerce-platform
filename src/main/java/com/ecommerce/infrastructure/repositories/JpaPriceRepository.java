package com.ecommerce.infrastructure.repositories;

import com.ecommerce.domain.entities.Price;
import com.ecommerce.domain.interfaces.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JpaPriceRepository implements PriceRepository {

    @Autowired
    private final BaseJpaPriceRepository base;

    @Autowired
    public JpaPriceRepository(BaseJpaPriceRepository base) {
        this.base = base;
    }

    @Override
    public Price findPriceByProductIdAndBrandIdAndDate(Integer productId, Integer brandId, String date) {
        return null;
    }
}