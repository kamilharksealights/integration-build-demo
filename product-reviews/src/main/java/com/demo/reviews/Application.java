package com.demo.reviews;

import com.google.gson.Gson;
import io.undertow.Undertow;
import io.undertow.server.RoutingHandler;

import static io.undertow.Handlers.routing;

public class Application {

    private Gson gson = new Gson();
    private ProductReviewsService productReviewsService = new ProductReviewsService();

    public static void main(String[] args) {
        int port = getPort();
        new Application().start(port);
    }

    private static int getPort() {
        try {
            return Integer.parseInt(System.getenv("PORT"));
        } catch (NumberFormatException e) {
            return 8079;
        }
    }

    private void start(int port) {
        RoutingHandler handler = routing();
        handler.post("/review", new AddReviewHandler(gson, productReviewsService));
        handler.get("/review/{productId}", new GetReviewHandler(gson, productReviewsService));

        Undertow server = Undertow.builder()
                .addHttpListener(port, "localhost")
                .setHandler(handler).build();
        server.start();
    }
}
