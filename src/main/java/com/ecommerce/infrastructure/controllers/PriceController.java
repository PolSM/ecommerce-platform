package com.ecommerce.infrastructure.controllers;

import com.ecommerce.application.dtos.PriceDTO;
import com.ecommerce.application.services.PriceService;
import com.ecommerce.infrastructure.utils.JsonConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping(value = "/price")
@Tag(name = "Price", description = "Price Managment API")
public class PriceController {

    @Autowired
    private PriceService priceService;

    @GetMapping
    @Operation(summary = "Get price by product ID, brand ID, and date", description = "Returns the price details for the specified product taking priority into account")
    public ResponseEntity getPrice(
            @RequestParam("product_id") Integer productId,
            @RequestParam("brand_id") Integer brandId,
            @RequestParam("date") LocalDateTime date
    ) {
        Optional<PriceDTO> priceDTO = priceService.getPrice(productId, brandId, date);
        System.out.println(priceDTO.toString());
        try {
            String json = JsonConverter.convertToJson(priceDTO.get());
            return ResponseEntity.ok(json);
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Price not found");
        }
    }
}