package com.example;

import org.junit.jupiter.api.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

class EdgeCaseTest {

    @Nested
    @DisplayName("Edge Cases for Warehouse and Products")
    class WarehouseEdgeCases {

        private Warehouse warehouse;

        @BeforeEach
        void setUp() {
            warehouse = Warehouse.getInstance("EdgeCaseWarehouse");
            warehouse.clearProducts();
        }

        @Test
        @DisplayName("❌ should throw IllegalArgumentException when adding null product")
        void should_throwException_when_addingNullProduct() {
            assertThatThrownBy(() -> warehouse.addProduct(null))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Product cannot be null.");
        }

        @Test
        @DisplayName("✅ should not crash when removing non-existent product")
        void should_notCrash_when_removingNonExistentProduct() {
            UUID fakeId = UUID.randomUUID();
            warehouse.remove(fakeId); // should not throw
            assertThat(warehouse.isEmpty()).isTrue();
        }
    }

    @Nested
    @DisplayName("Product Edge Cases")
    class ProductEdgeCases {

        @Test
        @DisplayName("❌ should throw when FoodProduct created with negative weight")
        void should_throw_when_negativeWeight() {
            assertThatThrownBy(() -> new FoodProduct(
                    UUID.randomUUID(),
                    "Air",
                    Category.of("Test"),
                    BigDecimal.TEN,
                    LocalDate.now(),
                    new BigDecimal("-5")
            ))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Weight cannot be negative.");
        }

        @Test
        @DisplayName("❌ should throw when ElectronicsProduct created with negative warranty")
        void should_throw_when_negativeWarranty() {
            assertThatThrownBy(() -> new ElectronicsProduct(
                    UUID.randomUUID(),
                    "Magic TV",
                    Category.of("Test"),
                    BigDecimal.TEN,
                    -1,
                    BigDecimal.ONE
            ))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Warranty months cannot be negative.");
        }

        @Test
        @DisplayName("❌ should throw when Product created with negative price")
        void should_throw_when_negativePrice() {
            assertThatThrownBy(() -> new FoodProduct(
                    UUID.randomUUID(),
                    "Rotten Banana",
                    Category.of("Fruit"),
                    new BigDecimal("-100"),
                    LocalDate.now(),
                    BigDecimal.ONE
            ))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Price cannot be negative.");
        }
    }
}
