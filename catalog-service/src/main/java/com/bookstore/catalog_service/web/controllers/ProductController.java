package com.bookstore.catalog_service.web.controllers;

import com.bookstore.catalog_service.domain.product.Product;
import com.bookstore.catalog_service.domain.product.ProductNotFoundException;
import com.bookstore.catalog_service.domain.product.ProductService;
import com.bookstore.catalog_service.domain.shared.PagedResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
class ProductController {
    private final ProductService productService;
    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    PagedResult<Product> getProducts(@RequestParam(name = "page", defaultValue = "1") int pageNo) {
        return productService.getProducts(pageNo);
    }

    @GetMapping("/{code}")
    ResponseEntity<Product> getProductByCode(@PathVariable String code) {
        return productService.getProductByCode(code)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ProductNotFoundException(code));
    }
}
