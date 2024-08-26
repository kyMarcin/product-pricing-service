package com.inpost.pricing.product_pricing_service.api.discountpolicy.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Schema(description = "Represents an absolute discount policy request")
public final class AbsoluteDiscountPolicyRequest extends DiscountPolicyRequest {


    @NotNull
    @DecimalMin(value = "0.01")
    private final BigDecimal absoluteDiscount;

    public AbsoluteDiscountPolicyRequest(
            Integer unitThreshold,
            BigDecimal absoluteDiscount) {
        super(unitThreshold);
        this.absoluteDiscount = absoluteDiscount;
    }
}
