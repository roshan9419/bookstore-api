package com.bookstore.catalog_service.domain.product;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String code) {
        super("Product not found with code: " + code);
    }
}
