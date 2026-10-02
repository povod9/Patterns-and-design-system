package org.example.epam;

public class Processor {
    public static ProcessingResult processOrders(Product[] products, Order[] orders) {
        OrderResult[] orderResultsResponse = new OrderResult[orders.length];
        int[] bestProducts = new int[products.length];
        int rejectedAmount = 0;
        int completedAmount = 0;
        int totalAmountOfSoldProduct = 0;
        for (int i = 0; i < orders.length; i++) {
            Order order = orders[i];
            int index = getProductIndex(products, orders[i].productName());
            Product product = getProductByName(products, order.productName());

            //If the requested quantity is less than or equal to zero, reject the order with OrderStatus.INVALID_QUANTITY.
            if (order.amountOfProduct() <= 0) {
                OrderResult orderResult = new OrderResult(order, OrderStatus.INVALID_QUANTITY);
                orderResultsResponse[i] = orderResult;
                ++rejectedAmount;
            }

            //Otherwise, if the product code is null or is not present in the warehouse, reject the order with OrderStatus.UNKNOWN_PRODUCT.
            else if (order.productName() == null || product == null) {
                OrderResult orderResult = new OrderResult(order, OrderStatus.UNKNOWN_PRODUCT);
                orderResultsResponse[i] = orderResult;
                ++rejectedAmount;
            }

            //Otherwise, if the available quantity is smaller than the requested quantity, reject the order with OrderStatus.NOT_ENOUGH_STOCK.
            else {
                if (product.productAmount() < order.amountOfProduct()) {
                    OrderResult orderResult = new OrderResult(order, OrderStatus.NOT_ENOUGH_STOCK);
                    orderResultsResponse[i] = orderResult;
                    ++rejectedAmount;
                }

                //Otherwise, complete the order with OrderStatus.COMPLETED and subtract the requested quantity from the corresponding Product object.
                else {
                    product.setProductAmount(product.productAmount() - order.amountOfProduct());
                    OrderResult orderResult = new OrderResult(order, OrderStatus.COMPLETED);
                    orderResultsResponse[i] = orderResult;
                    totalAmountOfSoldProduct += order.amountOfProduct();
                    bestProducts[index] += order.amountOfProduct();
                    ++completedAmount;
                }
            }
        }

        String bestSeller = getBestSeller(products, bestProducts);
        return new ProcessingResult(orderResultsResponse, completedAmount, rejectedAmount, totalAmountOfSoldProduct, bestSeller);

    }

    private static String getBestSeller(Product[] products, int[] bestProducts) {
        int bestIndex = 0;
        for (int i = 1; i < bestProducts.length; i++) {
            if (bestProducts[i] == 0) {
                continue;
            }
            if (bestProducts[bestIndex] < bestProducts[i]) {
                bestIndex = i;
            } else if (bestProducts[bestIndex] == bestProducts[i]) {
                if (products[i].productName().compareTo(products[bestIndex].productName()) < 0) {
                    bestIndex = i;
                }
            }
        }
        if (bestProducts[bestIndex] == 0) {
            return null;
        }
        return products[bestIndex].productName();
    }

    private static int getProductIndex(Product[] products, String name) {
        for (int i = 0; i < products.length; i++) {
            if (products[i].productName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    private static Product getProductByName(Product[] products, String orderProductName) {
        for (int i = 0; i < products.length; i++) {
            if (products[i].productName().equals(orderProductName)) {
                return products[i];
            }
        }
        return null;
    }
}
