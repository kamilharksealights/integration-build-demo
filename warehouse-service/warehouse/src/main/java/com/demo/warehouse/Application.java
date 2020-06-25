package com.demo.warehouse;

import com.demo.warehouse.catalog.ProductCatalog;
import com.demo.warehouse.catalog.ProductCatalogRepository;
import com.demo.warehouse.catalog.ProductValidator;
import com.demo.warehouse.orders.OrderHandler;
import com.demo.warehouse.shipping.ShippingService;
import com.google.gson.Gson;
import io.undertow.Undertow;
import io.undertow.server.RoutingHandler;

import static io.undertow.Handlers.routing;


public class Application {

    private final int servicePort;
    private final String reviewServiceHost;
    private final int reviewServicePort;
    private final Gson gson = new Gson();
    private final ProductCatalog productCatalog = new ProductCatalog(new ProductCatalogRepository(), new ProductValidator());
    private final OrderHandler orderHandler = new OrderHandler(productCatalog, new ShippingService());

    public Application(int servicePort, String reviewServiceHost, int reviewServicePort) {
        this.servicePort = servicePort;
        this.reviewServiceHost = reviewServiceHost;
        this.reviewServicePort = reviewServicePort;
    }

    private void start() {
        RoutingHandler handler = routing();
        handler.post("/product", new AddProductHandler(gson, productCatalog));
        handler.get("/products", new GetProductsHandler(gson, productCatalog, new ReviewServiceClient(this.reviewServiceHost, this.reviewServicePort, gson)));
        handler.post("/order", new PlaceOrderHandler(gson, orderHandler));

        Undertow server = Undertow.builder()
                .addHttpListener(servicePort, "localhost")
                .setHandler(handler).build();
        server.start();
    }

    public static void main(String[] args) {
        int servicePort = getPort("PORT", 8080);
        int reviewServicePort = getPort("REVIEW_SERVICE_PORT", 8079);
        String reviewServiceHost = getHost("REVIEW_SERVICE_HOST", "localhost");
        new Application(servicePort, reviewServiceHost, reviewServicePort).start();
    }

    private static String getHost(String envName, String defaultHost) {
        String host = System.getenv(envName);
        if (host != null) {
            return host;
        }
        return defaultHost;
    }

    private static int getPort(String envName, int defaultPort) {
        try {
            return Integer.parseInt(System.getenv(envName));
        } catch (NumberFormatException e) {
            return defaultPort;
        }
    }

}
