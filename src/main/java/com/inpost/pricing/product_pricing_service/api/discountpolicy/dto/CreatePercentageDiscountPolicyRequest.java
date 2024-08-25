package com.inpost.pricing.product_pricing_service.api.discountpolicy.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public final class CreatePercentageDiscountPolicyRequest extends CreateDiscountPolicyRequest {


    @NotNull
    @Min(1) @Max(100)
    private final Integer percentageDiscount;

    public CreatePercentageDiscountPolicyRequest(
            Integer unitThreshold,
            Integer percentageDiscount) {
        super(unitThreshold);
        this.percentageDiscount = percentageDiscount;
    }
}
