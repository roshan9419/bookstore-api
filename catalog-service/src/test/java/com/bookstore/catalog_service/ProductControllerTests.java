package com.bookstore.catalog_service;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.bookstore.catalog_service.domain.product.Product;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;

@Sql("/test-data.sql")
public class ProductControllerTests extends AbstractIT {

    @Test
    void shouldReturnProducts() {
        given().contentType(ContentType.JSON)
                .when()
                .get("/api/products")
                .then()
                .statusCode(200)
                .body("data", hasSize(10))
                .body("totalElements", is(19))
                .body("pageNumber", is(1))
                .body("totalPages", is(2))
                .body("isFirst", is(true))
                .body("isLast", is(false))
                .body("hasNext", is(true))
                .body("hasPrevious", is(false));
    }

    @Test
    void shouldReturnProductByCode() {
        String code = "P006";
        Product product = given().contentType(ContentType.JSON)
                .when()
                .get("/api/products/" + code)
                .then()
                .statusCode(200)
                .assertThat()
                .extract()
                .body()
                .as(Product.class);

        assertEquals(code, product.code());
        assertEquals("Gaming Console", product.name());
        assertEquals("Next-gen gaming console with 4K support", product.description());
        assertEquals("https://example.com/console.jpg", product.imageUrl());
        assertEquals(new BigDecimal("499.99"), product.price());
    }

    @Test
    void shouldReturnNotFoundWhenProductNotExists() {
        String invalidCode = "invalid-prd-code";
        given().contentType(ContentType.JSON)
                .when()
                .get("/api/products/" + invalidCode)
                .then()
                .statusCode(404)
                .body("title", is("Product Not Found"))
                .body("status", is(404))
                .body("detail", is("Product not found with code: " + invalidCode));
    }

}