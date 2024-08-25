package com.inpost.pricing.product_pricing_service.service;

import java.math.BigDecimal;

public record Product(String id, String name, BigDecimal price) {
}
