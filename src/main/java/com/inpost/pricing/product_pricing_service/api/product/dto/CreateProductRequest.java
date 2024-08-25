package com.inpost.pricing.product_pricing_service.api.product.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;

import java.math.BigDecimal;

public record CreateProductRequest(@NotEmpty String name, @DecimalMin(value = "0.01") BigDecimal price) {
}
