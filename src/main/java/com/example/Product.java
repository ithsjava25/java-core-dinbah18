package com.example;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class Product implements Shippable {
    private final UUID uuid;
    private final String name;
    private BigDecimal price;
    private final Category category;
    private final BigDecimal weight;

    public Product(String name, BigDecimal price, Category category) {
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.price = price;
        this.category = category;
        this.weight = BigDecimal.valueOf(1.0); // standardvikt
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal newPrice) {
        this.price = newPrice;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public BigDecimal calculateShippingCost() {
        // enkel fraktkostnadsberäkning
        return weight.multiply(BigDecimal.valueOf(10));
    }

    @Override
    public BigDecimal weight() {
        return weight;
    }

    // Metod som används i testerna
    public String productDetails() {
        return String.format("%s (%s): %s", name, category, price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return uuid.equals(product.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

    @Override
    public String toString() {
        return name + " - " + category + " (" + price + ")";
    }
}
