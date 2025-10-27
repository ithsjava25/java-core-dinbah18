package com.example;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class WarehouseAnalyzer {
    private final Warehouse warehouse;

    public WarehouseAnalyzer(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public List<String> getAllProductNames() {
        return warehouse.listProducts().stream()
                .map(Product::getName)
                .collect(Collectors.toList());
    }

    public Map<Category, Long> countProductsByCategory() {
        return warehouse.listProducts().stream()
                .collect(Collectors.groupingBy(Product::getCategory, Collectors.counting()));
    }

    public BigDecimal calculateAveragePrice() {
        return warehouse.listProducts().stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(BigDecimal.valueOf(Math.max(1, warehouse.listProducts().size())), BigDecimal.ROUND_HALF_UP);
    }

    public List<Product> findMostExpensiveProducts() {
        Optional<BigDecimal> max = warehouse.listProducts().stream()
                .map(Product::getPrice)
                .max(Comparator.naturalOrder());
        return max.map(price ->
                warehouse.listProducts().stream()
                        .filter(p -> p.getPrice().compareTo(price) == 0)
                        .toList()
        ).orElse(List.of());
    }
}
