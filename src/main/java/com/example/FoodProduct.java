package com.example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class FoodProduct extends Product implements Perishable {

    private final BigDecimal weight;
    private final LocalDate expirationDate;

    public FoodProduct(UUID id, String name, Category category, BigDecimal price, BigDecimal weight, LocalDate expirationDate) {
        super(id, name, category, price);
        this.weight = weight;
        this.expirationDate = expirationDate;
    }

    public BigDecimal weight() {
        return weight;
    }

    @Override
    public LocalDate expirationDate() {
        return expirationDate;
    }

    @Override
    public boolean isExpired() {
        return expirationDate.isBefore(LocalDate.now());
    }
}
