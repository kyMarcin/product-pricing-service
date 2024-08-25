package com.inpost.pricing.product_pricing_service.api.discountpolicy.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = CreateAbsoluteDiscountPolicyRequest.class, name = "absolute"),
        @JsonSubTypes.Type(value = CreatePercentageDiscountPolicyRequest.class, name = "percentage")
})
public sealed abstract class CreateDiscountPolicyRequest permits CreateAbsoluteDiscountPolicyRequest, CreatePercentageDiscountPolicyRequest {

    @NotNull
    @Min(1)
    private final Integer unitThreshold;
}
