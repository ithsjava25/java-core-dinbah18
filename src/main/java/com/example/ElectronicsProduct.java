package com.example;

import java.math.BigDecimal;
import java.util.UUID;

public class ElectronicsProduct extends Product {
    private final BigDecimal powerUsage;

    // Den här konstruktorn används i testerna (6 parametrar)
    public ElectronicsProduct(UUID id, String name, Category category, BigDecimal price, int watt, BigDecimal weight) {
        super(name, price, category);
        this.powerUsage = BigDecimal.valueOf(watt);
    }

    public ElectronicsProduct(UUID id, String name, Category category, BigDecimal price, BigDecimal powerUsage) {
        super(name, price, category);
        this.powerUsage = powerUsage;
    }

    public BigDecimal getPowerUsage() {
        return powerUsage;
    }

    @Override
    public String toString() {
        return getName() + " (" + getCategory() + ") - " + powerUsage + "W - " + getPrice();
    }
}
