package com.bookstore.catalog_service.domain.product;

import java.math.BigDecimal;

public record Product(
        String code,
        String name,
        String description,
        String imageUrl,
        BigDecimal price
) {
}
