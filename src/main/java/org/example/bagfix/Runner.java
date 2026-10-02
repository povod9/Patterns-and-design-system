package org.example.bagfix;

import java.time.Duration;
import java.util.stream.IntStream;

/**
 * DO NOT TOUCH THIS FILE!
 */
public class Runner {

    static int PRODUCTS_SIZE = 10;

    public static void main(String[] args) {
        final var products = IntStream.rangeClosed(1, PRODUCTS_SIZE)
                .mapToObj(id -> new Product("Product-" + id, id))
                .toList();

        final var categoryRepository = new CategoryRepository();
        final var externalCategoryClient = new ExternalService();
        final var service = new ProductSyncService(
                externalCategoryClient,
                categoryRepository);

        TimerUtils.measure("Product synchronization", () -> {
            service.synchronize(products);
            return null;
        }, Duration.ofSeconds(0));
    }
}
