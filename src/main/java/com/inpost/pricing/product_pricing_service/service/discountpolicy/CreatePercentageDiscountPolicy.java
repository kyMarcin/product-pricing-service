package com.inpost.pricing.product_pricing_service.service.discountpolicy;

import lombok.Getter;


@Getter
public final class CreatePercentageDiscountPolicy extends CreateDiscountPolicy {
    ;
    private final Integer percentageDiscount;

    public CreatePercentageDiscountPolicy(Integer unitThreshold, Integer percentageDiscount) {
        super(unitThreshold);
        this.percentageDiscount = percentageDiscount;
    }
}
