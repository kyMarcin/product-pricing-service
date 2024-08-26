package com.inpost.pricing.product_pricing_service.service.discountpolicy;

import java.math.BigDecimal;
import java.util.Objects;

public record DiscountPolicy(String id,
                             Integer unitThreshold,
                             BigDecimal absoluteDiscount,
                             Integer percentageDiscount) {

    public enum DiscountPolicyType {
        ABSOLUTE {
            @Override
            public BigDecimal calculate(BigDecimal price, Integer amount, BigDecimal absoluteDiscount, Integer percentageDiscount) {
                return price.multiply(BigDecimal.valueOf(amount)).subtract(absoluteDiscount);
            }
        },
        PERCENTAGE {
            @Override
            public BigDecimal calculate(BigDecimal price, Integer amount, BigDecimal absoluteDiscount, Integer percentageDiscount) {
                BigDecimal discountFactor = BigDecimal.valueOf(100 - percentageDiscount).divide(BigDecimal.valueOf(100));
                return price.multiply(BigDecimal.valueOf(amount)).multiply(discountFactor);
            }
        };

        public abstract BigDecimal calculate(BigDecimal price, Integer amount, BigDecimal absoluteDiscount, Integer percentageDiscount);
    }

    public DiscountPolicyType getType() {
        if (Objects.isNull(absoluteDiscount) && Objects.isNull(percentageDiscount)) {
            throw new IllegalStateException("Discount has to be of a certain type!");
        } else if (Objects.isNull(absoluteDiscount)) {
            return DiscountPolicyType.PERCENTAGE;
        } else {
            return DiscountPolicyType.ABSOLUTE;
        }
    }

    public BigDecimal calculateTotalPrice(BigDecimal price, Integer amount) {
        if (unitThreshold > amount) {
            throw new IllegalArgumentException("Could not calculate total price - amount is below threshold");
        }

        return getType().calculate(price, amount, absoluteDiscount, percentageDiscount);
    }
}