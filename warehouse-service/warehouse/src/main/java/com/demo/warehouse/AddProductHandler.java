package com.demo.warehouse;

import com.demo.warehouse.catalog.Product;
import com.demo.warehouse.catalog.ProductCatalog;
import com.google.gson.Gson;
import io.undertow.io.Receiver;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;

class AddProductHandler implements HttpHandler {

    private Gson gson;
    private ProductCatalog productCatalog;

    public AddProductHandler(Gson gson, ProductCatalog productCatalog) {
        this.gson = gson;
        this.productCatalog = productCatalog;
    }

    @Override
    public void handleRequest(HttpServerExchange exchange) {
        exchange.getRequestReceiver().receiveFullString(handleRequestWithBody());
    }

    private Receiver.FullStringCallback handleRequestWithBody() {
        return (exchange, message) -> {
            Product product = gson.fromJson(message, Product.class);
            if (AddProductHandler.this.productCatalog.addProduct(product)) {
                exchange.setStatusCode(200);
            } else {
                exchange.setStatusCode(400);
            }
            exchange.endExchange();
        };
    }
}
