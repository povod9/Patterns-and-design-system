package org.example.epam;

public record OrderResult(
        Order order,
        OrderStatus status
) {
}
