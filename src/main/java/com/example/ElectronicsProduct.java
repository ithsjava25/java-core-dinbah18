package com.example;

import java.math.BigDecimal;
import java.util.UUID;

public class ElectronicsProduct extends Product {

    private final BigDecimal weight;
    private final int warrantyMonths;

    public ElectronicsProduct(UUID id, String name, Category category, BigDecimal price, BigDecimal weight, int warrantyMonths) {
        super(id, name, category, price);
        this.weight = weight;
        this.warrantyMonths = warrantyMonths;
    }

    public BigDecimal weight() {
        return weight;
    }

    public int warrantyMonths() {
        return warrantyMonths;
    }
}
