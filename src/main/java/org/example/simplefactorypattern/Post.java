package org.example.simplefactorypattern;

import java.math.BigDecimal;

public class Post implements Delivery{
    @Override
    public int maxWeight() {
        return 10;
    }

    @Override
    public BigDecimal calculateCost(Order order) {
        return BigDecimal.valueOf(order.kilo());
    }
}
