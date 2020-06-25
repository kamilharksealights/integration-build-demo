package com.demo.warehouse.shipping;

import java.util.LinkedList;
import java.util.Queue;

public class ShippingService {

    private Queue<Shipping> shippingQueue = new LinkedList<>();

    public void shipProduct(Shipping shipping) {
        shippingQueue.offer(shipping);
    }

}
