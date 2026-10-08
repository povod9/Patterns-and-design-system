package org.example.simplefactorypattern;

import java.math.BigDecimal;

public class Courier implements Delivery {
    @Override
    public int maxWeight() {
        return 20;
    }

    @Override
    public BigDecimal calculateCost(Order order) {
        return BigDecimal.valueOf(4 + order.kilo());
    }
}
