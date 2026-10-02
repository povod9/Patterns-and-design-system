package org.example.shopingcart;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Cart {
    List<Product> products = new ArrayList<>();

    public Cart(List<Product> products) {
        this.products = products;
    }

    public Cart() {
    }

    public void addProduct(Product product){
        if(products.contains(product)){
            product.setAmountOfProduct(product.getAmountOfProduct() + 1);
            return;
        }
        products.add(product);
        product.setAmountOfProduct(product.getAmountOfProduct() + 1);
    }

    public void removeProduct(Product product){
        products.remove(product);
        product.setAmountOfProduct(product.getAmountOfProduct() - 1);
    }

    public void deleteWholeAmountOfProduct(){
        products.clear();
    }

    @Override
    public String toString() {
        return "Cart{" +
                "products=" + products + "\n" +
                '}';
    }
}
