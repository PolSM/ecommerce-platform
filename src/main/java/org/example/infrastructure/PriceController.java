package org.example.infrastructure;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/price")
public class PriceController {
    @GetMapping
    public String getPrice(
            @RequestParam("product_id") String productId,
            @RequestParam("brand_id") String brandId,
            @RequestParam("date") String date
    ) {
        return "a price";
    }
}