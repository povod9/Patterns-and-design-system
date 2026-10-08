package org.example.simplefactorypattern;

import java.math.BigDecimal;

public interface Delivery  {
    int maxWeight();
    BigDecimal calculateCost(Order order);
    default void deliver(Order order){
        if(order.kilo() > maxWeight()){
            throw new IllegalArgumentException("The order weight can't be bigger than: " + maxWeight());
        }
        if(order.kilo() <= 0){
            throw new IllegalArgumentException("The order weight can't be less than: 0");
        }
        System.out.println("Payment for the delivery: " + calculateCost(order) + "$");
    }

}
