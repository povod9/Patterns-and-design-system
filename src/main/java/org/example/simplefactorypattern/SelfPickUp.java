package org.example.simplefactorypattern;

import java.math.BigDecimal;

public class SelfPickUp implements Delivery{
    @Override
    public int maxWeight() {
        return 30;
    }

    @Override
    public BigDecimal calculateCost(Order order) {
        return BigDecimal.ZERO;
    }
}
