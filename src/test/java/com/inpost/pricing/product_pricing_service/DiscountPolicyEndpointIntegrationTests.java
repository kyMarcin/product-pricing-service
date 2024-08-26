package com.inpost.pricing.product_pricing_service;

import com.inpost.pricing.product_pricing_service.api.discountpolicy.dto.AbsoluteDiscountPolicyRequest;
import com.inpost.pricing.product_pricing_service.api.discountpolicy.dto.DiscountPolicyRequest;
import com.inpost.pricing.product_pricing_service.api.discountpolicy.dto.DiscountPolicyResponse;
import com.inpost.pricing.product_pricing_service.api.discountpolicy.dto.PercentageDiscountPolicyRequest;
import com.inpost.pricing.product_pricing_service.repository.discountpolicy.DiscountPolicyJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;


@Sql(
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = "classpath:discount-policy.sql"
)
@Sql(
        executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
        scripts = "classpath:cleanup-tables.sql"
)
class DiscountPolicyEndpointIntegrationTests extends AbstractIntegrationTest {

    @Autowired
    DiscountPolicyJpaRepository repository;

    @Test
    void shouldReturn201WhenCreateAbsolutePolicyRequestIsValid() {
        // given
        var discountPolicy = new AbsoluteDiscountPolicyRequest(3, BigDecimal.TEN);
        // when
        var response = postForEntity("/api/discount-policy", discountPolicy, DiscountPolicyResponse.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        String discountPolicyId = response.getBody().id();
        assertThat(discountPolicyId).isNotEmpty();
        assertThat(repository.existsById(UUID.fromString(discountPolicyId))).isTrue();
        assertThat(response.getBody().absoluteDiscount()).isEqualTo(BigDecimal.TEN);
        assertThat(response.getBody().percentageDiscount()).isNull();
        assertThat(response.getBody().unitThreshold()).isEqualTo(3);
        assertThat(response.getBody().type()).isEqualTo(DiscountPolicyResponse.DiscountPolicyType.absolute);
    }

    @Test
    void shouldReturn201WhenCreatePercentagePolicyRequestIsValid() {
        // given
        var discountPolicy = new PercentageDiscountPolicyRequest(10, 50);
        // when
        var response = postForEntity("/api/discount-policy", discountPolicy, DiscountPolicyResponse.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        String discountPolicyId = response.getBody().id();
        assertThat(discountPolicyId).isNotEmpty();
        assertThat(repository.existsById(UUID.fromString(discountPolicyId))).isTrue();
        assertThat(response.getBody().percentageDiscount()).isEqualTo(50);
        assertThat(response.getBody().absoluteDiscount()).isNull();
        assertThat(response.getBody().unitThreshold()).isEqualTo(10);
        assertThat(response.getBody().type()).isEqualTo(DiscountPolicyResponse.DiscountPolicyType.percentage);
    }

    @Test
    void shouldConvertAbsolutePolicyToPercentagePolicyWhenUpdatedById() {
        // given
        var discountPolicy = new PercentageDiscountPolicyRequest(5, 20);
        // when
        var response = putForEntity("/api/discount-policy/550e8400-e29b-41d4-a716-446655440000", discountPolicy, DiscountPolicyResponse.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        String discountPolicyId = response.getBody().id();
        assertThat(discountPolicyId).isNotEmpty();
        assertThat(repository.existsById(UUID.fromString(discountPolicyId))).isTrue();
        assertThat(response.getBody().percentageDiscount()).isEqualTo(20);
        assertThat(response.getBody().absoluteDiscount()).isNull();
        assertThat(response.getBody().unitThreshold()).isEqualTo(5);
        assertThat(response.getBody().type()).isEqualTo(DiscountPolicyResponse.DiscountPolicyType.percentage);
    }

    @Test
    void shouldRemovePolicyWhenDeleteRequestIsPerformed() {
        // when
        restTemplate.delete("/api/discount-policy/550e8400-e29b-41d4-a716-446655440000");

        assertThat(repository.existsById(UUID.fromString("550e8400-e29b-41d4-a716-446655440000"))).isFalse();
    }

    @ParameterizedTest
    @MethodSource("invalidCreateDiscountPolicyBody")
    void shouldReturn400ForInvalidPayload(DiscountPolicyRequest discountPolicy) {
        // when
        var response = postForEntity("/api/discount-policy", discountPolicy, DiscountPolicyResponse.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    private static Stream<DiscountPolicyRequest> invalidCreateDiscountPolicyBody() {
        return Stream.of(
                new AbsoluteDiscountPolicyRequest(0, BigDecimal.TEN),
                new AbsoluteDiscountPolicyRequest(10, BigDecimal.ZERO),
                new PercentageDiscountPolicyRequest(-5, 10),
                new PercentageDiscountPolicyRequest(10, 0),
                new PercentageDiscountPolicyRequest(10, 110)
        );
    }

}
