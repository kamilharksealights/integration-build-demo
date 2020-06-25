package com.demo.warehouse.catalog;

import java.util.List;

public class ProductCatalog {

    private final ProductCatalogRepository productCatalogRepository;
    private final ProductValidator productValidator;

    public ProductCatalog(ProductCatalogRepository productCatalogRepository, ProductValidator productValidator) {
        this.productCatalogRepository = productCatalogRepository;
        this.productValidator = productValidator;
    }

    public boolean addProduct(Product product) {
        if (this.productValidator.isValid(product)) {
            this.productCatalogRepository.saveOrUpdate(product);
            return true;
        } else {
            return false;
        }
    }

    public List<Product> getAll() {
        return this.productCatalogRepository.getAll();
    }

    public Product getById(String productId) {
        return this.productCatalogRepository.getById(productId);
    }

    public void decreaseStock(String productId, int count) {
        Product product = this.getById(productId);
        if (product != null) {
            if (product.getStock() < count) {
                throw new RuntimeException("no sufficient stock");
            }
            product.setStock(product.getStock() - count);
        }
    }
}
