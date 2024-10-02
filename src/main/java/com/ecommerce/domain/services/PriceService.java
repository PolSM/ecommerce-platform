package com.ecommerce.domain.services;

import com.ecommerce.application.dtos.PriceDTO;
import com.ecommerce.domain.entities.Price;
import com.ecommerce.infrastructure.repositories.JpaPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PriceService {

    private final JpaPriceRepository priceRepository;

    @Autowired
    public PriceService(JpaPriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public Optional<PriceDTO> getPrice(Integer productId, Integer brandId, LocalDateTime date) {
        Optional<Price> price = priceRepository.findPriceByProductIdAndBrandIdAndDate(productId, brandId, date);
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