package com.inpost.pricing.product_pricing_service.service;

import java.math.BigDecimal;

public record CreateProduct(String name, BigDecimal price) {
}
