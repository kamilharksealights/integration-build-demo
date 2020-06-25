package com.demo.reviews;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import java.util.Collection;

public class ProductReviewsService {

    private Multimap<String, Review> reviews = HashMultimap.create();

    public void addReview(Review review) {
        this.reviews.put(review.getProductId(), review);
    }

    public Collection<Review> getProductReviews(String productId) {
        return reviews.get(productId);
    }

}
