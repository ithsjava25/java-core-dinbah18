package com.example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public final class FoodProduct extends Product implements Perishable, Shippable {

    private final LocalDate expirationDate;
    private final BigDecimal weightKg;

    public FoodProduct(UUID id, String name, Category category, BigDecimal price,
                       LocalDate expirationDate, BigDecimal weightKg) {

        super(id, name, category, price);

        if (weightKg.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Weight cannot be negative.");
        }

        this.expirationDate = expirationDate;
        this.weightKg = weightKg;
    }

    @Override
    public LocalDate expirationDate() {
        return expirationDate;
    }

    @Override
    public BigDecimal calculateShippingCost() {
        return weightKg.multiply(BigDecimal.valueOf(50));
    }

    @Override
    public String productDetails() {
        return "Food: " + name() + ", Expires: " + expirationDate;
    }
}
