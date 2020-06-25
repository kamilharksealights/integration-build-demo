package com.demo.warehouse.catalog;

public class ProductValidator {

    public boolean isValid(Product product) {
        return product.getStock() >= 0;
    }
}
