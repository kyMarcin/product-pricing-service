package com.inpost.pricing.product_pricing_service.service.product;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends IllegalArgumentException {

    public ProductNotFoundException() {
        super("Product has not been found");
    }
}
