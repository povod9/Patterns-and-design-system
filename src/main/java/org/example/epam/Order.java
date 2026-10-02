package org.example.epam;

public record Order(
        String orderId,
        String productName,
        int amountOfProduct
) {
}
