package com.inpost.pricing.product_pricing_service.service.discountpolicy;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class DiscountPolicyTest {

    @Test
    void shouldReturnAbsoluteTypeWhenAbsoluteDiscountIsSet() {
        // given
        DiscountPolicy policy = new DiscountPolicy("1", 10, new BigDecimal("20.00"), null);

        // when
        DiscountPolicy.DiscountPolicyType type = policy.getType();

        // then
        assertEquals(DiscountPolicy.DiscountPolicyType.ABSOLUTE, type);
    }

    @Test
    void shouldReturnPercentageTypeWhenPercentageDiscountIsSet() {
        // given
        DiscountPolicy policy = new DiscountPolicy("2", 10, null, 15);

        // when
        DiscountPolicy.DiscountPolicyType type = policy.getType();

        // then
        assertEquals(DiscountPolicy.DiscountPolicyType.PERCENTAGE, type);
    }

    @Test
    void shouldThrowExceptionWhenBothDiscountsAreNull() {
        // given
        DiscountPolicy policy = new DiscountPolicy("3", 10, null, null);

        // when & then
        assertThrows(IllegalStateException.class, policy::getType);
    }

    @Test
    void shouldCalculateTotalPriceWithAbsoluteDiscount() {
        // given
        DiscountPolicy policy = new DiscountPolicy("4", 10, new BigDecimal("20.00"), null);
        BigDecimal price = new BigDecimal("100.00");
        Integer amount = 15;

        // when
        BigDecimal totalPrice = policy.calculateTotalPrice(price, amount);

        // then
        assertEquals(new BigDecimal("1480.00"), totalPrice);
    }

    @Test
    void shouldCalculateTotalPriceWithPercentageDiscount() {
        // given
        DiscountPolicy policy = new DiscountPolicy("5", 10, null, 10);
        BigDecimal price = new BigDecimal("100.00");
        Integer amount = 15;

        // when
        BigDecimal totalPrice = policy.calculateTotalPrice(price, amount);

        // then
        assertEquals(new BigDecimal("1350.000"), totalPrice);
    }

    @Test
    void shouldThrowExceptionWhenAmountIsBelowThreshold() {
        // given
        DiscountPolicy policy = new DiscountPolicy("6", 10, new BigDecimal("20.00"), null);
        BigDecimal price = new BigDecimal("100.00");
        Integer amount = 5;

        // when & then
        assertThrows(IllegalArgumentException.class, () -> policy.calculateTotalPrice(price, amount));
    }

    @Test
    void shouldHandleZeroAbsoluteDiscountGracefully() {
        // given
        DiscountPolicy policy = new DiscountPolicy("7", 10, BigDecimal.ZERO, null);
        BigDecimal price = new BigDecimal("100.00");
        Integer amount = 15;

        // when
        BigDecimal totalPrice = policy.calculateTotalPrice(price, amount);

        // then
        assertEquals(new BigDecimal("1500.00"), totalPrice);
    }

    @Test
    void shouldHandleZeroPercentageDiscountGracefully() {
        // given
        DiscountPolicy policy = new DiscountPolicy("8", 10, null, 0);
        BigDecimal price = new BigDecimal("100.00");
        Integer amount = 15;

        // when
        BigDecimal totalPrice = policy.calculateTotalPrice(price, amount);

        // then
        assertEquals(new BigDecimal("1500.00"), totalPrice);
    }

    @Test
    void shouldCalculateTotalPriceForEdgeCaseAmountEqualToThreshold() {
        // given
        DiscountPolicy policy = new DiscountPolicy("9", 10, new BigDecimal("50.00"), null);
        BigDecimal price = new BigDecimal("100.00");
        Integer amount = 10;

        // when
        BigDecimal totalPrice = policy.calculateTotalPrice(price, amount);

        // then
        assertEquals(new BigDecimal("950.00"), totalPrice);
    }

}
