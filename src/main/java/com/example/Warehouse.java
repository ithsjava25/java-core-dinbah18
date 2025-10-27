package com.example;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class Warehouse {
    private static Warehouse instance;
    private final Map<UUID, Product> products = new HashMap<>();

    // Testerna anropar getInstance(String)
    public static Warehouse getInstance(String name) {
        if (instance == null) {
            instance = new Warehouse();
        }
        return instance;
    }

    public void clearProducts() {
        products.clear();
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

    public void addProduct(Product product) {
        products.put(product.getUuid(), product);
    }

    public Map<UUID, Product> getProducts() {
        return products;
    }

    public void removeProduct(UUID id) {
        products.remove(id);
    }

    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(products.get(id));
    }

    public void updateProductPrice(UUID id, BigDecimal newPrice) {
        Product p = products.get(id);
        if (p != null) {
            p.setPrice(newPrice);
        }
    }

    public Map<Category, List<Product>> getProductsGroupedByCategories() {
        return products.values().stream()
                .collect(Collectors.groupingBy(Product::getCategory));
    }

    public List<Product> expiredProducts() {
        return products.values().stream()
                .filter(p -> p instanceof Perishable per && per.isExpired())
                .collect(Collectors.toList());
    }

    public List<Product> shippableProducts() {
        return products.values().stream()
                .filter(p -> p instanceof Shippable)
                .collect(Collectors.toList());
    }

    // 🔹 Används av WarehouseAnalyzer och tester (det som saknades!)
    public List<Product> listProducts() {
        return new ArrayList<>(products.values());
    }
}
