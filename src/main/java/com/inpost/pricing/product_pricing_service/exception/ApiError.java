package com.inpost.pricing.product_pricing_service.exception;

import org.springframework.http.HttpStatus;

public record ApiError(HttpStatus status, String message) {
}
