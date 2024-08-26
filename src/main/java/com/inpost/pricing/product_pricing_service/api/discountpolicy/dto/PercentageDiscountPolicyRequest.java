package com.inpost.pricing.product_pricing_service.api.discountpolicy.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@Schema(description = "Represents a percentage discount policy request")
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
