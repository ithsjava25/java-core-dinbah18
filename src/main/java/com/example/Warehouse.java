package com.example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public final class Warehouse {

    private static Warehouse instance;
    private final String name;
    private final Map<UUID, Product> products = new HashMap<>();

    private Warehouse(String name) {
        this.name = name;
    }

    // Singleton
    public static Warehouse getInstance(String name) {
        if (instance == null) {
            instance = new Warehouse(name);
        }
        return instance;
    }

    // G-level
    public boolean isEmpty() {
        return products.isEmpty();
    }

    public void clearProducts() {
        products.clear();
    }

    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null.");
        }
        products.put(product.uuid(), product);
    }

    public void remove(UUID id) {
        products.remove(id);
    }

    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(products.get(id));
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(new ArrayList<>(products.values()));
    }

    public void updateProductPrice(UUID id, BigDecimal price) {
        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }

        Product p = products.get(id);
        if (p == null) {
            throw new NoSuchElementException("Product not found with id: " + id);
        }
    }

    // VG-level
    public Map<Category, List<Product>> getProductsGroupedByCategories() {
        return products.values().stream()
                .collect(Collectors.groupingBy(Product::category));
    }

    public List<Perishable> expiredProducts() {
        LocalDate today = LocalDate.now();

        return products.values().stream()
                .filter(p -> p instanceof Perishable per && per.expirationDate().isBefore(today))
                .map(p -> (Perishable) p)
                .toList();
    }

    public List<Shippable> shippableProducts() {
        return products.values().stream()
                .filter(p -> p instanceof Shippable)
                .map(p -> (Shippable) p)
                .toList();
    }
}
