package com.inpost.pricing.product_pricing_service.service.pricing;

import com.inpost.pricing.product_pricing_service.service.discountpolicy.DiscountPolicy;
import com.inpost.pricing.product_pricing_service.service.discountpolicy.DiscountPolicyService;
import com.inpost.pricing.product_pricing_service.service.product.Product;
import com.inpost.pricing.product_pricing_service.service.product.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PricingService {

    private final ProductService productService;
    private final DiscountPolicyService discountPolicyService;

    public BigDecimal calculateTotalPrice(String productId, String discountPolicyId, Integer amount) {

        Product product = productService.get(productId);

        BigDecimal totalPrice = Optional.ofNullable(discountPolicyId).map(dpi -> {
            DiscountPolicy discountPolicy = discountPolicyService.get(discountPolicyId);
            validateThreshold(amount, discountPolicy);
            return discountPolicy.calculateTotalPrice(product.price(), amount);
        }).orElseGet(() -> product.price().multiply(BigDecimal.valueOf(amount)));

        validateIfPositive(totalPrice);
        return totalPrice;
    }

    private static void validateIfPositive(BigDecimal totalPrice) {
        if(totalPrice.signum() <= 0) {
            throw new TotalPriceBelowZeroException();
        }
    }

    private static void validateThreshold(Integer amount, DiscountPolicy discountPolicy) {
        if (amount < discountPolicy.unitThreshold()) {
            throw new DiscountNotEligibleForProductException();
        }

    }
}
