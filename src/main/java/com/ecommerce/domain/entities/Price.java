package com.ecommerce.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "tbl_prices", schema = "ecommerce_platform")
public class Price {
    @Id
    @Column(name = "product_id")
    private Integer productId;
    @Column(name = "brand_id")
    private Integer brandId;
    @Column(name = "start_date")
    private LocalDateTime startDate;
    @Column(name = "end_date")
    private LocalDateTime endDate;
    @Column(name = "price_list")
    private Integer priceList;
    @Column(name = "priority")
    private Byte priority;
    @Column(name = "price")
    private Float price;
    @Column(name = "currency")
    private String currency;

    public Price() {
    }

    public Price(Integer productId, Integer brandId, LocalDateTime startDate, LocalDateTime endDate, Integer priceList, Byte priority, Float price, String currency) {
        this.productId = productId;
        this.brandId = brandId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priceList = priceList;
        this.priority = priority;
        this.price = price;
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Price price1 = (Price) o;
        return Objects.equals(productId, price1.productId) && Objects.equals(brandId, price1.brandId) && Objects.equals(startDate, price1.startDate) && Objects.equals(endDate, price1.endDate) && Objects.equals(priceList, price1.priceList) && Objects.equals(priority, price1.priority) && Objects.equals(price, price1.price) && Objects.equals(currency, price1.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, brandId, startDate, endDate, priceList, priority, price, currency);
    }
}
