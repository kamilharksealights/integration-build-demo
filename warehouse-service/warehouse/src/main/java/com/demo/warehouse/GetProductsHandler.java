package com.demo.warehouse;

import com.demo.warehouse.catalog.Product;
import com.demo.warehouse.catalog.ProductCatalog;
import com.google.gson.Gson;
import io.undertow.io.Receiver;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;

import java.util.List;
import java.util.stream.Collectors;

public class GetProductsHandler implements HttpHandler {

    private Gson gson;
    private ProductCatalog productCatalog;
    private ReviewServiceClient reviewServiceClient;

    public GetProductsHandler(Gson gson, ProductCatalog productCatalog, ReviewServiceClient reviewServiceClient) {
        this.gson = gson;
        this.productCatalog = productCatalog;
        this.reviewServiceClient = reviewServiceClient;
    }

    @Override
    public void handleRequest(HttpServerExchange exchange) {
        exchange.getRequestReceiver().receiveFullString(handleRequestWithBody());
    }

    private Receiver.FullStringCallback handleRequestWithBody() {
        return (exchange, message) -> {
            List<Product> products = productCatalog.getAll();

            List<ProductWithReviews> result = products.stream()
                    .map(product -> {
                        List<Review> reviews = this.reviewServiceClient.getForProduct(product);
                        ProductWithReviews productWithReviews = new ProductWithReviews();
                        productWithReviews.setProduct(product);
                        productWithReviews.setReviews(reviews);
                        return productWithReviews;
                    }).collect(Collectors.toList());

            exchange.getResponseSender().send(gson.toJson(result));
            exchange.endExchange();
        };
    }
}
