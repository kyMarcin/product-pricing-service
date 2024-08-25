package com.inpost.pricing.product_pricing_service.service.discountpolicy;

import lombok.Getter;

import java.math.BigDecimal;


@Getter
public final class CreateAbsoluteDiscountPolicy extends CreateDiscountPolicy {
    private final BigDecimal absoluteDiscount;

    public CreateAbsoluteDiscountPolicy(Integer unitThreshold, BigDecimal absoluteDiscount) {
        super(unitThreshold);
        this.absoluteDiscount = absoluteDiscount;
    }
}
