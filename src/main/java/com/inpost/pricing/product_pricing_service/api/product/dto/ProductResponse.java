package com.inpost.pricing.product_pricing_service.api.product.dto;

import java.math.BigDecimal;

public record ProductResponse(String id, String name, BigDecimal price) {
}
