package com.demo.reviews;

import com.google.gson.Gson;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;

import java.util.Collection;

public class GetReviewHandler implements HttpHandler {

    private final Gson gson;
    private final ProductReviewsService productReviewsService;

    public GetReviewHandler(Gson gson, ProductReviewsService productReviewsService) {
        this.gson = gson;
        this.productReviewsService = productReviewsService;
    }

    @Override
    public void handleRequest(HttpServerExchange exchange) {
        String productId = exchange.getQueryParameters().get("productId").element();
        Collection<Review> reviews = productReviewsService.getProductReviews(productId);
        String json = gson.toJson(reviews);
        System.out.println("======== got request ===============");
        System.out.println(json);
        System.out.println("======== got request ===============");
        exchange.getResponseHeaders().put(HttpString.tryFromString("Content-type"), "application/json");
        exchange.getResponseSender().send(json);
        exchange.endExchange();
    }
}
