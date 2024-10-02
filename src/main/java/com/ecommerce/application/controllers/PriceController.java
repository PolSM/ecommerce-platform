package com.ecommerce.application.controllers;

import com.ecommerce.application.dtos.PriceDTO;
import com.ecommerce.domain.services.PriceService;
import com.ecommerce.infrastructure.utils.JsonConverter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved price", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PriceDTO.class))),
            @ApiResponse(responseCode = "404", description = "Price not found", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity getPrice(
            @RequestParam("product_id") Integer productId,
            @RequestParam("brand_id") Integer brandId,
            @RequestParam("date") LocalDateTime date
    ) {
        Optional<PriceDTO> priceDTO = priceService.getPrice(productId, brandId, date);
        if (priceDTO.isPresent()) {
            try {
                String json = JsonConverter.convertToJson(priceDTO.get());
                return ResponseEntity.ok(json);
            } catch (Exception e) {
                return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}