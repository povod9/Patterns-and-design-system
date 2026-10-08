package org.example.simplefactorypattern;

public class DeliveryFactory {
    static void main() {
        Order order1 = createOrder("Jana Pawla II", 15);
        Order order2 = createOrder("Jana Pawla II", 0);
        Order order3 = createOrder("Jana Pawla II", 9);
        createDelivery("courier").deliver(order1);
        createDelivery("selfpickup").deliver(order2);
        createDelivery("post").deliver(order3);
    }


    public static Delivery createDelivery(String deliveryType){
        if(deliveryType == null || deliveryType.isEmpty()){
            throw new IllegalArgumentException("Delivery type must be pointed!");
        }
        return switch (deliveryType.toLowerCase()){
            case "courier" -> new Courier();
            case "selfpickup" -> new SelfPickUp();
            case "post" -> new Post();
            default -> throw new IllegalStateException("Unexpected value: " + deliveryType.toLowerCase());
        };
    }

    public static Order createOrder(String address, int kilo){
        if(kilo < 0){
            throw new IllegalArgumentException("The order is too small for delivery");
        }
        return new Order(address,kilo);
    }
}
