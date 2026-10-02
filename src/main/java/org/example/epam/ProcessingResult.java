package org.example.epam;

public record ProcessingResult(
        OrderResult[] result,
        int completedOrders,
        int rejectedOrders,
        int totalAmountOfSoldProduct,
        String bestSelling
) {
}
