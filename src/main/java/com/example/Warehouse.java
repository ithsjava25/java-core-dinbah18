package com.example;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public final class Warehouse {

    private static Warehouse instance;
    private final String name;
    private final Map<UUID, Product> products = new HashMap<>();

    private Warehouse(String name) {
        this.name = name;
    }

    public static Warehouse getInstance(String name) {
        if (instance == null) {
            instance = new Warehouse(name);
        }
        return instance;
    }

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
        Product p = products.get(id);
        if (p == null) {
            throw new NoSuchElementException("Product not found with id: " + id);
        }
    }

    public Map<Category, List<Product>> getProductsGroupedByCategories() {
        return products.values().stream()
                .collect(Collectors.groupingBy(Product::category));
    }

    public List<Perishable> expiredProducts() {
        return products.values().stream()
                .filter(p -> p instanceof Perishable per && per.expirationDate().isBefore(java.time.LocalDate.now()))
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
