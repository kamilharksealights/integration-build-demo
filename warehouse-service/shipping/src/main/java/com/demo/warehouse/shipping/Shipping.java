package com.demo.warehouse.shipping;

import java.util.Objects;

public class Shipping {

    private String productId;
    private String destination;
    private int count;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shipping shipping = (Shipping) o;
        return count == shipping.count &&
                Objects.equals(productId, shipping.productId) &&
                Objects.equals(destination, shipping.destination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, destination, count);
    }
}
