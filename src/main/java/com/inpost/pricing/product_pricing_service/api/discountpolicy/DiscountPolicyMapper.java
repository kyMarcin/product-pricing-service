package com.inpost.pricing.product_pricing_service.api.discountpolicy;

import com.inpost.pricing.product_pricing_service.api.discountpolicy.dto.CreateAbsoluteDiscountPolicyRequest;
import com.inpost.pricing.product_pricing_service.api.discountpolicy.dto.CreateDiscountPolicyRequest;
import com.inpost.pricing.product_pricing_service.api.discountpolicy.dto.CreatePercentageDiscountPolicyRequest;
import com.inpost.pricing.product_pricing_service.api.discountpolicy.dto.DiscountPolicyResponse;
import com.inpost.pricing.product_pricing_service.service.discountpolicy.CreateAbsoluteDiscountPolicy;
import com.inpost.pricing.product_pricing_service.service.discountpolicy.CreateDiscountPolicy;
import com.inpost.pricing.product_pricing_service.service.discountpolicy.CreatePercentageDiscountPolicy;
import com.inpost.pricing.product_pricing_service.service.discountpolicy.DiscountPolicy;
import org.springframework.stereotype.Component;

import static com.inpost.pricing.product_pricing_service.api.discountpolicy.dto.DiscountPolicyResponse.DiscountPolicyType.absolute;
import static com.inpost.pricing.product_pricing_service.api.discountpolicy.dto.DiscountPolicyResponse.DiscountPolicyType.percentage;

@Component
public class DiscountPolicyMapper {

    public CreateDiscountPolicy map(CreateDiscountPolicyRequest request) {
        return switch (request) {
            case CreateAbsoluteDiscountPolicyRequest d ->
                    new CreateAbsoluteDiscountPolicy(d.getUnitThreshold(), d.getAbsoluteDiscount());
            case CreatePercentageDiscountPolicyRequest d ->
                    new CreatePercentageDiscountPolicy(d.getUnitThreshold(), d.getPercentageDiscount());
        };
    }

    public DiscountPolicyResponse map(DiscountPolicy discountPolicy) {
        var policyType = switch (discountPolicy.getType()) {
            case ABSOLUTE -> absolute;
            case PERCENTAGE -> percentage;
        };
        return new DiscountPolicyResponse(
                discountPolicy.id(),
                discountPolicy.unitThreshold(),
                discountPolicy.absoluteDiscount(),
                discountPolicy.percentageDiscount(),
                policyType
                );
    }



}
