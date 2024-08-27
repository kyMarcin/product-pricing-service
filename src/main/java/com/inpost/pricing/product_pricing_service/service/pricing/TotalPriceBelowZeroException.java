package com.inpost.pricing.product_pricing_service.service.pricing;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TotalPriceBelowZeroException extends IllegalArgumentException {

    public TotalPriceBelowZeroException() {
        super("Total price cannot be lower than 0");
    }
}
