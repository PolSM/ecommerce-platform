package com.ecommerce.infrastructure.repositories;

import com.ecommerce.domain.entities.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BaseJpaPriceRepository extends JpaRepository<Price, Integer> {
    @Query("SELECT p FROM Price p WHERE p.startDate <= :date AND p.endDate >= :date AND p.productId = :productId AND p.brandId = :brandId")
    Optional<List<Price>> findPricesByProductIdAndBrandIdAndDate(@Param("productId") Integer productId, @Param("brandId") Integer brandId, @Param("date") LocalDateTime date);
}