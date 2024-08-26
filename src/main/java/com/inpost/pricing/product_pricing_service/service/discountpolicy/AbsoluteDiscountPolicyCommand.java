package com.inpost.pricing.product_pricing_service.service.discountpolicy;

import lombok.Getter;

import java.math.BigDecimal;


@Getter
public final class AbsoluteDiscountPolicyCommand extends DiscountPolicyCommand {
    private final BigDecimal absoluteDiscount;

    public AbsoluteDiscountPolicyCommand(Integer unitThreshold, BigDecimal absoluteDiscount) {
        super(unitThreshold);
        this.absoluteDiscount = absoluteDiscount;
    }
}
