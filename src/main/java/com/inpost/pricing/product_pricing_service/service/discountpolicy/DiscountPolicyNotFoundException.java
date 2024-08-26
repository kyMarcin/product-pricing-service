package com.inpost.pricing.product_pricing_service.service.discountpolicy;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DiscountPolicyNotFoundException extends IllegalArgumentException {
}
