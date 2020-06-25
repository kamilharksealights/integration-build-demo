package com.demo.warehouse;

import com.demo.warehouse.orders.OrderHandler;
import com.google.gson.Gson;
import io.undertow.io.Receiver;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;

import java.util.Map;

public class PlaceOrderHandler implements HttpHandler {

    private final Gson gson;
    private final OrderHandler orderHandler;

    public PlaceOrderHandler(Gson gson, OrderHandler orderHandler) {
        this.gson = gson;
        this.orderHandler = orderHandler;
    }

    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {
        exchange.getRequestReceiver().receiveFullString(handleRequestWithBody());
    }

    private Receiver.FullStringCallback handleRequestWithBody() {
        return (exchange, message) -> {
            Map<String, Object> orderDetails = gson.fromJson(message, Map.class);
            boolean added = orderHandler.addOrder((String) orderDetails.get("productId"),
                    (int) (double) orderDetails.get("count"), (String) orderDetails.get("destination"));
            if (added) {
                exchange.setStatusCode(200);
            } else {
                exchange.setStatusCode(400);
            }
            exchange.endExchange();
        };
    }
}
