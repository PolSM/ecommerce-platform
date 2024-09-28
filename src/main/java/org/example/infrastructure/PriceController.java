package org.example.infrastructure;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prices")
public class PriceController {
    @GetMapping
    public String getPrices(@RequestParam("application_date") String applicationDate,
                            @RequestParam("product_id") String productId,
                            @RequestParam("brand_id") String brandId) {
        return "List of prices";
    }
}