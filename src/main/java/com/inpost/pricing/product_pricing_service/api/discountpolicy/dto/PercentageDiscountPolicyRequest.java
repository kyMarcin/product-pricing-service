package com.inpost.pricing.product_pricing_service.api.discountpolicy.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public final class PercentageDiscountPolicyRequest extends DiscountPolicyRequest {


    @NotNull
    @Min(1) @Max(100)
    private final Integer percentageDiscount;

    public PercentageDiscountPolicyRequest(
            Integer unitThreshold,
            Integer percentageDiscount) {
        super(unitThreshold);
        this.percentageDiscount = percentageDiscount;
    }
}
