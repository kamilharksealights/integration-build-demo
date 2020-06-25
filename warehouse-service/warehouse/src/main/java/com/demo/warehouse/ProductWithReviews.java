package com.demo.warehouse;

import com.demo.warehouse.catalog.Product;

import java.util.List;
import java.util.Objects;

public class ProductWithReviews {

    private Product product;
    private List<Review> reviews;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductWithReviews that = (ProductWithReviews) o;
        return Objects.equals(product, that.product) &&
                Objects.equals(reviews, that.reviews);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, reviews);
    }
}
