package com.inpost.pricing.product_pricing_service.api.discountpolicy.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.v3.oas.annotations.media.DiscriminatorMapping;
import io.swagger.v3.oas.annotations.media.Schema;
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
        @JsonSubTypes.Type(value = AbsoluteDiscountPolicyRequest.class, name = "absolute"),
        @JsonSubTypes.Type(value = PercentageDiscountPolicyRequest.class, name = "percentage")
})
@Schema(
        description = "Base type for discount policy requests",
        //TODO: it still does not show properly on UI swagger - to be fixed
        discriminatorProperty = "type",
        discriminatorMapping = {
                @DiscriminatorMapping(value = "absolute", schema = AbsoluteDiscountPolicyRequest.class),
                @DiscriminatorMapping(value = "percentage", schema = PercentageDiscountPolicyRequest.class)
        },
        example = """
            {
                "type": "absolute",
                "unitThreshold": 10,
                "absoluteDiscount": 15.0
            }
    """
)
public sealed abstract class DiscountPolicyRequest permits AbsoluteDiscountPolicyRequest, PercentageDiscountPolicyRequest {

    @NotNull
    @Min(1)
    private final Integer unitThreshold;
}
