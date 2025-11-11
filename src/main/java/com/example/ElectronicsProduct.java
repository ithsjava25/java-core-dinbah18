package com.example;

import java.math.BigDecimal;
import java.util.UUID;

public final class ElectronicsProduct extends Product implements Shippable {

    private final int warrantyMonths;
    private final BigDecimal weightKg;

    public ElectronicsProduct(UUID id, String name, Category category, BigDecimal price,
                              int warrantyMonths, BigDecimal weightKg) {

        super(id, name, category, price);

        if (warrantyMonths < 0) {
            throw new IllegalArgumentException("Warranty months cannot be negative.");
        }

        this.warrantyMonths = warrantyMonths;
        this.weightKg = weightKg;
    }

    @Override
    public BigDecimal calculateShippingCost() {
        // Shipping: 79kr + 49kr (heavy surcharge)
        return BigDecimal.valueOf(79).add(BigDecimal.valueOf(49));
    }

    @Override
    public String productDetails() {
        return "Electronics: " + name() + ", Warranty: " + warrantyMonths + " months";
    }
}
