package com.inpost.pricing.product_pricing_service.api.discountpolicy;

import com.inpost.pricing.product_pricing_service.api.discountpolicy.dto.AbsoluteDiscountPolicyRequest;
import com.inpost.pricing.product_pricing_service.api.discountpolicy.dto.DiscountPolicyRequest;
import com.inpost.pricing.product_pricing_service.api.discountpolicy.dto.DiscountPolicyResponse;
import com.inpost.pricing.product_pricing_service.api.discountpolicy.dto.PercentageDiscountPolicyRequest;
import com.inpost.pricing.product_pricing_service.service.discountpolicy.AbsoluteDiscountPolicyCommand;
import com.inpost.pricing.product_pricing_service.service.discountpolicy.DiscountPolicy;
import com.inpost.pricing.product_pricing_service.service.discountpolicy.DiscountPolicyCommand;
import com.inpost.pricing.product_pricing_service.service.discountpolicy.PercentageDiscountPolicyCommand;
import org.springframework.stereotype.Component;

import static com.inpost.pricing.product_pricing_service.api.discountpolicy.dto.DiscountPolicyResponse.DiscountPolicyType.absolute;
import static com.inpost.pricing.product_pricing_service.api.discountpolicy.dto.DiscountPolicyResponse.DiscountPolicyType.percentage;

@Component
public class DiscountPolicyMapper {

    public DiscountPolicyCommand map(DiscountPolicyRequest request) {
        return switch (request) {
            case AbsoluteDiscountPolicyRequest d ->
                    new AbsoluteDiscountPolicyCommand(d.getUnitThreshold(), d.getAbsoluteDiscount());
            case PercentageDiscountPolicyRequest d ->
                    new PercentageDiscountPolicyCommand(d.getUnitThreshold(), d.getPercentageDiscount());
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
