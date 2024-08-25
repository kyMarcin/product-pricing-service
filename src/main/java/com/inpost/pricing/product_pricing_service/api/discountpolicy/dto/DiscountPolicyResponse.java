package com.inpost.pricing.product_pricing_service.api.discountpolicy.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record DiscountPolicyResponse(
        String id,
        Integer unitThreshold,
        BigDecimal absoluteDiscount,
        Integer percentageDiscount,
        DiscountPolicyType type
) {

    public enum DiscountPolicyType {
        absolute,
        percentage
    }
}
