package com.example;

public enum Category {
    FOOD,
    ELECTRONICS,
    CLOTHING,
    HOME,
    OTHER;

    public static Category of(String name) {
        if (name == null) return OTHER;
        try {
            return Category.valueOf(name.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            return OTHER;
        }
    }

    // Tester använder category.getName()
    public String getName() {
        return name();
    }
}
