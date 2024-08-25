package com.inpost.pricing.product_pricing_service.service.discountpolicy;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public sealed abstract class CreateDiscountPolicy permits CreateAbsoluteDiscountPolicy, CreatePercentageDiscountPolicy {
    private final Integer unitThreshold;
}
