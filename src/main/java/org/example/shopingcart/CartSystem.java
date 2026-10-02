package org.example.shopingcart;


public class CartSystem {
    static void main() {
        Cart cart = new Cart();
        Product apple = new Product("Apple");
        Product apple2 = new Product("Apple");
        Product cola = new Product("Cola");

        cart.addProduct(apple);
        System.out.println(cart.toString());

        cart.addProduct(apple);
        System.out.println(cart.toString());

        cart.addProduct(apple2);
        System.out.println(cart.toString());

        cart.addProduct(cola);
        System.out.println(cart.toString());

        cart.removeProduct(apple);
        System.out.println(cart.toString());

        cart.deleteWholeAmountOfProduct();
        System.out.println(cart.toString());
    }
}
