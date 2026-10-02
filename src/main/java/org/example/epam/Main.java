package org.example.epam;


public class Main {
    public static void main(String[] args) {
        final var products = new Product[] {
                new Product("PEN", 0),
                new Product("BOOK", 0)
        };

        final var orders = new Order[] {
                new Order("102", "BOOK", 5),
                new Order("103", "PEN", 17),
                new Order("104", "UNKNOWN", 1),
                new Order("105", "BOOK", 10),
                new Order("101", "PEN", 3)

        };

        final var result = Processor.processOrders(products, orders);

        for (final var orderResult : result.result()) {
            if(orderResult == null) continue;
            System.out.println("Order " + orderResult.order() + " -> " + orderResult.status());
        }

        System.out.println("Completed: " + result.completedOrders());
        System.out.println("Rejected: " + result.rejectedOrders());
        System.out.println("Total units sold: " + result.totalAmountOfSoldProduct());
        System.out.println("Best seller: " + result.bestSelling());

        for (final var product : products) {
            System.out.println(product.productName() + " remaining: " + product.productAmount());
        }
    }
}
