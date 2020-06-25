package com.demo.warehouse;

import java.util.Objects;

public class Review {
    private String productId;
    private String comment;
    private int rating;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return rating == review.rating &&
                Objects.equals(productId, review.productId) &&
                Objects.equals(comment, review.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, comment, rating);
    }

}
