package com.bookstore.order_service.domain;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String orderId) {
        super("Order not found with Id: " + orderId);
    }
}
