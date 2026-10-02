package org.example.shopingcart;

import java.util.Objects;

public class Product {
    private final String productName;
    private int amountOfProduct;

    public Product(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    public int getAmountOfProduct() {
        return amountOfProduct;
    }

    public void setAmountOfProduct(int amountOfProduct) {
        this.amountOfProduct = amountOfProduct;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return  Objects.equals(productName, product.productName);
    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(productName);
//    }

    @Override
    public String toString() {
        return productName + " - " + amountOfProduct + "\n";
    }
}
