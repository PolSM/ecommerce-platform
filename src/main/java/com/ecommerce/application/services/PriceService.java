package com.ecommerce.application.services;

import com.ecommerce.application.dtos.PriceDTO;
import com.ecommerce.domain.entities.Price;
import com.ecommerce.infrastructure.repositories.BaseJpaPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PriceService {

    private final BaseJpaPriceRepository priceRepository;

    @Autowired
    public PriceService(BaseJpaPriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public Optional<PriceDTO> getPrice(LocalDateTime date, Integer productId, Integer brandId) {
        Optional<Price> price = priceRepository.findPriceByProductIdAndBrandIdAndDate(date, productId, brandId);
        return price.map(p -> new PriceDTO(
                p.productId(),
                p.brandId(),
                p.startDate(),
                p.endDate(),
                p.price(),
                p.currency()
        ));
    }
}