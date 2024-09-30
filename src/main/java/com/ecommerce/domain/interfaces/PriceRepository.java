package com.ecommerce.domain.interfaces;

import com.ecommerce.domain.entities.Price;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository {
    Price findPriceByProductIdAndBrandIdAndDate(Integer productId, Integer brandId, String date);
}
