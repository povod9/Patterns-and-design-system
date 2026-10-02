package org.example.bagfix;

import java.util.Objects;

/**
 * DO NOT TOUCH THIS FILE!
 */
public final class Product {
    private String name;
    private int categoryId;
    private boolean synced;

    public Product(String name, int categoryId) {
        this.name = name;
        this.categoryId = categoryId;
    }

    public void markAsSynced() {
        this.synced = true;
    }

    public String name() {
        return name;
    }

    public int categoryId() {
        return categoryId;
    }

    public boolean isSynced() {
        return synced;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Product) obj;
        return Objects.equals(this.name, that.name) &&
                this.categoryId == that.categoryId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, categoryId);
    }

    @Override
    public String toString() {
        return "Product[" +
                "name=" + name + ", " +
                "categoryId=" + categoryId + ']';
    }

}
