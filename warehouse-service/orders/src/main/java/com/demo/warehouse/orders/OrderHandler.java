package com.demo.warehouse.orders;

import com.demo.warehouse.catalog.ProductCatalog;
import com.demo.warehouse.shipping.Shipping;
import com.demo.warehouse.shipping.ShippingService;

public class OrderHandler {

    private final ProductCatalog productCatalog;
    private ShippingService shippingService;

    public OrderHandler(ProductCatalog productCatalog, ShippingService shippingService) {
        this.productCatalog = productCatalog;
        this.shippingService = shippingService;
    }

    public boolean addOrder(String productId, int count, String destination) {
        try {
            productCatalog.decreaseStock(productId, count);
            Shipping shipping = new Shipping();
            shipping.setProductId(productId);
            shipping.setDestination(destination);
            shipping.setCount(count);
            shippingService.shipProduct(shipping);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
