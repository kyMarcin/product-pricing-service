package com.inpost.pricing.product_pricing_service;

import com.inpost.pricing.product_pricing_service.api.discountpolicy.dto.CreateAbsoluteDiscountPolicyRequest;
import com.inpost.pricing.product_pricing_service.api.discountpolicy.dto.CreateDiscountPolicyRequest;
import com.inpost.pricing.product_pricing_service.api.discountpolicy.dto.CreatePercentageDiscountPolicyRequest;
import com.inpost.pricing.product_pricing_service.api.discountpolicy.dto.DiscountPolicyResponse;
import com.inpost.pricing.product_pricing_service.repository.discountpolicy.DiscountPolicyJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class DiscountPolicyEndpointIntegrationTests extends AbstractIntegrationTest {

    @Autowired
    DiscountPolicyJpaRepository repository;

    @Test
    void shouldReturn201WhenCreateAbsolutePolicyRequestIsValid() {
        // given
        var discountPolicy = new CreateAbsoluteDiscountPolicyRequest(3, BigDecimal.TEN);
        // when
        var response = postForEntity("/api/discount-policy", discountPolicy, DiscountPolicyResponse.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @ParameterizedTest
    @MethodSource("invalidCreateDiscountPolicyBody")
    void shouldReturn400ForInvalidPayload(CreateDiscountPolicyRequest discountPolicy) {
        // when
        var response = postForEntity("/api/discount-policy", discountPolicy, DiscountPolicyResponse.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    private static Stream<CreateDiscountPolicyRequest> invalidCreateDiscountPolicyBody() {
        return Stream.of(
                new CreateAbsoluteDiscountPolicyRequest(0, BigDecimal.TEN),
                new CreateAbsoluteDiscountPolicyRequest(10, BigDecimal.ZERO),
                new CreatePercentageDiscountPolicyRequest(-5, 10),
                new CreatePercentageDiscountPolicyRequest(10, 0),
                new CreatePercentageDiscountPolicyRequest(10, 110)
        );
    }

}
