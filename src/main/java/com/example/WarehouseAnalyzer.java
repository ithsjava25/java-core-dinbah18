package com.example;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class WarehouseAnalyzer {

    private final Warehouse warehouse;

    public WarehouseAnalyzer(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public BigDecimal totalValue() {
        return warehouse.getProducts().stream()
                .map(Product::price)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Optional<Product> mostExpensiveProduct() {
        return warehouse.getProducts().stream()
                .max(Comparator.comparing(Product::price));
    }

    public Optional<Product> cheapestProduct() {
        return warehouse.getProducts().stream()
                .min(Comparator.comparing(Product::price));
    }

    public long expiredProductsCount() {
        return warehouse.getProducts().stream()
                .filter(p -> p instanceof Perishable perishable && perishable.isExpired())
                .count();
    }

    public List<Product> products() {
        return warehouse.getProducts();
    }
}
