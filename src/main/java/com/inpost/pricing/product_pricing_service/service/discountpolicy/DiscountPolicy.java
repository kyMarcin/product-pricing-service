package com.inpost.pricing.product_pricing_service.service.discountpolicy;

import java.math.BigDecimal;
import java.util.Objects;

public record DiscountPolicy(String id,
                             Integer unitThreshold,
                             BigDecimal absoluteDiscount,
                             Integer percentageDiscount) {

    public enum DiscountPolicyType {
        ABSOLUTE,
        PERCENTAGE
    }


    public DiscountPolicyType getType() {
        if (Objects.isNull(absoluteDiscount) && Objects.isNull(percentageDiscount)) {
            throw new IllegalStateException("Discount have to be of a certain type!");
        } else if (Objects.isNull(absoluteDiscount)) {
            return DiscountPolicyType.PERCENTAGE;
        } else {
            return DiscountPolicyType.ABSOLUTE;
        }
    }
}
