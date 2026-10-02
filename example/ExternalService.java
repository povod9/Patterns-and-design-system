package org.example;

import java.time.Duration;
import java.util.Random;

/**
 * DO NOT TOUCH THIS FILE!
 */
public class ExternalService {

    private static final Random random = new Random();

    // Returns a category from external service in 1 second
    public String getCategory(int categoryId) {
        return TimerUtils.measure("externalGetCategory", () -> "ExternalCategory-" + categoryId, Duration.ofSeconds(1));
    }

    // Syncs a product with external service in 100 ms
    public void syncProduct(final Product product, final String category) throws ProductAlreadySyncedException {
        if (random.nextBoolean()) {
            throw new ProductAlreadySyncedException("Product %s already synced".formatted(product.name()));
        }
        TimerUtils.measure("externalSyncProduct", () -> {
            System.out.printf("Syncing product %s successfully%n", product.name());
            return null;
        }, Duration.ofMillis(100));
    }
}
