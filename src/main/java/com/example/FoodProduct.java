package com.example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class FoodProduct extends Product implements Perishable {
    private final LocalDate expirationDate;

    // Den här konstruktorn används i testerna (6 parametrar)
    public FoodProduct(UUID id, String name, Category category, BigDecimal price, LocalDate expirationDate, BigDecimal weight) {
        super(name, price, category);
        this.expirationDate = expirationDate;
    }

    // Reservkonstruktor (används ej i test men bra att ha)
    public FoodProduct(UUID id, String name, Category category, BigDecimal price, LocalDate expirationDate) {
        this(id, name, category, price, expirationDate, BigDecimal.ONE);
    }

    @Override
    public LocalDate expirationDate() {
        return expirationDate;
    }

    @Override
    public boolean isExpired() {
        return expirationDate.isBefore(LocalDate.now());
    }

    @Override
    public String toString() {
        return getName() + " (" + getCategory() + ") expires " + expirationDate + " - " + getPrice();
    }
}
