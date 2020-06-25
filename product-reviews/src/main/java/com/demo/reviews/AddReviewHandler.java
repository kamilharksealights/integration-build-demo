package com.demo.reviews;

import com.google.gson.Gson;
import io.undertow.io.Receiver;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;

public class AddReviewHandler implements HttpHandler {

    private final Gson gson;
    private final ProductReviewsService productReviewsService;

    public AddReviewHandler(Gson gson, ProductReviewsService productReviewsService) {
        this.gson = gson;
        this.productReviewsService = productReviewsService;
    }

    @Override
    public void handleRequest(HttpServerExchange exchange) {
        exchange.getRequestReceiver().receiveFullString(handleRequestWithBody());
    }

    private Receiver.FullStringCallback handleRequestWithBody() {
        return (exchange, message) -> {
            Review review = gson.fromJson(message, Review.class);
            productReviewsService.addReview(review);
            exchange.setStatusCode(200);
            exchange.endExchange();
        };
    }
}
