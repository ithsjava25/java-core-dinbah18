package com.example;

import java.math.BigDecimal;
import java.util.*;

public class Warehouse {

    private final List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(UUID id) {
        products.removeIf(p -> p.id().equals(id));
    }

    public Optional<Product> findProduct(UUID id) {
        return products.stream().filter(p -> p.id().equals(id)).findFirst();
    }

    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }

    public void applyDiscount(BigDecimal percent) {
        for (Product p : products) {
            BigDecimal newPrice = p.price().multiply(BigDecimal.ONE.subtract(percent.divide(BigDecimal.valueOf(100))));
            p.setPrice(newPrice);
        }
    }

    public List<Product> shippableProducts() {
        return products;
    }
}
