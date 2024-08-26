package com.inpost.pricing.product_pricing_service.service.pricing;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DiscountNotEligibleForProductException extends IllegalArgumentException {

    public DiscountNotEligibleForProductException() {
        super("Discount is not eligible for product - amount is lower than threshold");
    }
}
