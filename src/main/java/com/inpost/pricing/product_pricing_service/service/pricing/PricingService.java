package com.inpost.pricing.product_pricing_service.service.pricing;

import com.inpost.pricing.product_pricing_service.service.discountpolicy.DiscountPolicy;
import com.inpost.pricing.product_pricing_service.service.discountpolicy.DiscountPolicyService;
import com.inpost.pricing.product_pricing_service.service.product.Product;
import com.inpost.pricing.product_pricing_service.service.product.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@AllArgsConstructor
@Service
public class PricingService {

    private final ProductService productService;
    private final DiscountPolicyService discountPolicyService;

    public BigDecimal calculateTotalPrice(String productId, String discountPolicyId, Integer amount) {
        Product product = productService.get(productId);
        DiscountPolicy discountPolicy = discountPolicyService.get(discountPolicyId);
        validateThreshold(amount, discountPolicy);

        return discountPolicy.calculateTotalPrice(product.price(), amount);
    }

    private static void validateThreshold(Integer amount, DiscountPolicy discountPolicy) {
        if (amount < discountPolicy.unitThreshold()) {
            throw new DiscountNotEligibleForProductException();
        }

    }
}
