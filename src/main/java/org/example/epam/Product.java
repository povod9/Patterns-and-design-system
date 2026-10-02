package org.example.epam;

import java.util.Objects;

public final class Product {
    private final String productName;
    private int productAmount;

    public Product(
            String productName,
            int productAmount
    ) {
        this.productName = productName;
        this.productAmount = productAmount;
    }

    public String productName() {
        return productName;
    }

    public int productAmount() {
        return productAmount;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Product) obj;
        return Objects.equals(this.productName, that.productName) &&
                this.productAmount == that.productAmount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName, productAmount);
    }

    @Override
    public String toString() {
        return "Product[" +
                "productName=" + productName + ", " +
                "productAmount=" + productAmount + ']';
    }

    public void setProductAmount(int productAmount) {
        this.productAmount = productAmount;
    }
}
