package com.bookstore.catalog_service.domain.product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Sql("/test-data.sql")
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void shouldGetAllProducts() {
        List<ProductEntity> products = productRepository.findAll();
        assertEquals(19, products.size());
    }

    @Test
    void shouldGetProductByCode() {
        ProductEntity product = productRepository.findByCode("P006").orElseThrow();
        assertEquals("P006", product.getCode());
        assertEquals("Gaming Console", product.getName());
        assertEquals("Next-gen gaming console with 4K support", product.getDescription());
        assertEquals("https://example.com/console.jpg", product.getImageUrl());
        assertEquals(new BigDecimal("499.99"), product.getPrice());
    }

    @Test
    void shouldReturnEmptyWhenProductCodeNotExists() {
        assertTrue(productRepository.findByCode("invalid_prd_code").isEmpty());
    }
}