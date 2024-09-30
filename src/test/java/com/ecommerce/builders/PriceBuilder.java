package com.ecommerce.builders;

import com.ecommerce.domain.entities.Price;

import java.time.LocalDateTime;

public class PriceBuilder {
    private Integer productId = 35455;
    private Integer brandId = 1;
    private LocalDateTime startDate = LocalDateTime.of(2021, 1, 1, 0, 0);
    private LocalDateTime endDate = LocalDateTime.of(2021, 12, 31, 23, 59);
    private Integer priceList = 1;
    private Integer priority = 0;
    private Float price = 35.50f;
    private String currency = "EUR";

    public static Price aPrice() {
        return new PriceBuilder().build();
    }

    public PriceBuilder setProductId(Integer productId) {
        this.productId = productId;
        return this;
    }

    public PriceBuilder setBrandId(Integer brandId) {
        this.brandId = brandId;
        return this;
    }

    public PriceBuilder setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    public PriceBuilder setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
        return this;
    }

    public PriceBuilder setPriceList(Integer priceList) {
        this.priceList = priceList;
        return this;
    }

    public PriceBuilder setPriority(Integer priority) {
        this.priority = priority;
        return this;
    }

    public PriceBuilder setPrice(Float price) {
        this.price = price;
        return this;
    }

    public PriceBuilder setCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public Price build() {
        return new Price(productId, brandId, startDate, endDate, priceList, priority, price, currency);
    }
}