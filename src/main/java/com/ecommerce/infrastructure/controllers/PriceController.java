package com.ecommerce.infrastructure.controllers;

import com.ecommerce.application.dtos.PriceDTO;
import com.ecommerce.application.services.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping(
        method = RequestMethod.GET,
        value = "/price")
public class PriceController {

    @Autowired
    private PriceService priceService;

    @GetMapping
    public ResponseEntity getPrice(
            @RequestParam("product_id") Integer productId,
            @RequestParam("brand_id") Integer brandId,
            @RequestParam("date") LocalDateTime date
    ) {
        Optional<PriceDTO> priceDTO = priceService.getPrice(date, productId, brandId);
        if (priceDTO.isEmpty()) {
            return ResponseEntity.status(404).body("Price not found");
        }
        return ResponseEntity.ok(priceDTO.get());
    }
}