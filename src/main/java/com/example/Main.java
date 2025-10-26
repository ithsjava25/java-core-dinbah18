package com.example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        // Skapa ett nytt lager
        Warehouse warehouse = new Warehouse();

        // Skapa några produkter
        Product apple = new FoodProduct(
                UUID.randomUUID(),
                "Äpple",
                Category.FOOD,
                new BigDecimal("10.00"),
                new BigDecimal("0.2"),
                LocalDate.now().plusDays(3)
        );

        Product milk = new FoodProduct(
                UUID.randomUUID(),
                "Mjölk",
                Category.FOOD,
                new BigDecimal("15.00"),
                new BigDecimal("1.0"),
                LocalDate.now().minusDays(1) // utgången
        );

        Product laptop = new ElectronicsProduct(
                UUID.randomUUID(),
                "Laptop",
                Category.ELECTRONICS,
                new BigDecimal("12000.00"),
                new BigDecimal("1.5"),
                24
        );

        // Lägg till i lagret
        warehouse.addProduct(apple);
        warehouse.addProduct(milk);
        warehouse.addProduct(laptop);

        // Analysera lagret
        WarehouseAnalyzer analyzer = new WarehouseAnalyzer(warehouse);

        // Utskrifter
        System.out.println("Totalt värde: " + analyzer.totalValue() + " kr");
        analyzer.mostExpensiveProduct().ifPresent(p -> System.out.println("Dyrast: " + p));
        analyzer.cheapestProduct().ifPresent(p -> System.out.println("Billigast: " + p));
        System.out.println("Antal utgångna produkter: " + analyzer.expiredProductsCount());

        // Visa alla produkter
        System.out.println("\nAlla produkter i lagret:");
        for (Product p : analyzer.products()) {
            System.out.println("- " + p);
        }
    }
}
