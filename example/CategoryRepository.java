package org.example;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;

/**
 * DO NOT TOUCH THIS FILE!
 */
public class CategoryRepository {

    private static final Random random = new Random();

    private static final List<String> CATEGORIES = List.of("Category 1", "Category 2", "Category 3");

    // Returns all categories in 2 seconds
    public List<String> findAll() {
        return TimerUtils.measure("findAllCategories", () -> CATEGORIES, Duration.ofSeconds(2));
    }

    // Returns a category in 500 ms
    public Optional<String> findById(int id) {
        return TimerUtils.measure("findCategoryById", () -> {
            final var categoryId = Math.floorMod(id, CATEGORIES.size());

            if (random.nextBoolean()) {
                return ofNullable(CATEGORIES.get(categoryId));
            }

            return empty();
        }, Duration.ofMillis(500));
    }
}
