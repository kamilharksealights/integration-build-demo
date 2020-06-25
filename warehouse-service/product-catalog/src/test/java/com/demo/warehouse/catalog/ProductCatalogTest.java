package com.demo.warehouse.catalog;

import org.junit.Test;

import static org.junit.Assert.*;

public class ProductCatalogTest {

    private ProductCatalog productCatalog = new ProductCatalog(new ProductCatalogRepository(), new ProductValidator());

    @Test
    public void shouldNotAddProductIfInvalidStock() {
        Product product = new Product();
        product.setStock(-1);

        boolean added = productCatalog.addProduct(product);

        assertFalse(added);
    }
}