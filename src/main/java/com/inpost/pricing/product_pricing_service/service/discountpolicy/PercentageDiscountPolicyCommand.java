package com.inpost.pricing.product_pricing_service.service.discountpolicy;

import lombok.Getter;


@Getter
public final class PercentageDiscountPolicyCommand extends DiscountPolicyCommand {

    private final Integer percentageDiscount;

    public PercentageDiscountPolicyCommand(Integer unitThreshold, Integer percentageDiscount) {
        super(unitThreshold);
        this.percentageDiscount = percentageDiscount;
    }
}
