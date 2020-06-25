package com.demo.warehouse.catalog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductCatalogRepository {

    private Map<String, Product> products = new HashMap<>();

    public void saveOrUpdate(Product product) {
        products.put(product.getId(), product);
    }

    public List<Product> getAll() {
        return new ArrayList<>(products.values());
    }

    public Product getById(String productId) {
        return products.get(productId);
    }

}
