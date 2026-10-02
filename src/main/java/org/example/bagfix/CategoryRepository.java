package org.example.bagfix;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * DO NOT TOUCH THIS FILE!
 */
public class CategoryRepository {

    private static final Random random = new Random();

    private static final List<Category> CATEGORIES = List.of(
            new Category(1, "Category 1"),
            new Category(2, "Category 2"),
            new Category(3, "Category 3")
    );
    // Returns all categories in 2 seconds
    public List<Category> findAll() {
        return TimerUtils.measure("findAllCategories", () -> CATEGORIES, Duration.ofSeconds(2));
    }

    // Returns a category in 500 ms
    public Optional<Category> findById(final int id) {
        return TimerUtils.measure("findCategoryById", () -> {
            if (random.nextBoolean()) {
                return Optional.empty();
            }

            final var categoryId = Math.floorMod(id - 1, CATEGORIES.size()) + 1;

            return CATEGORIES.stream()
                    .filter(category -> category.id() == categoryId)
                    .findFirst();
        }, Duration.ofMillis(500));
    }
}
