package com.inpost.pricing.product_pricing_service;

import com.inpost.pricing.product_pricing_service.api.pricing.dto.TotalPriceResponse;
import com.inpost.pricing.product_pricing_service.repository.discountpolicy.DiscountPolicyJpaRepository;
import com.inpost.pricing.product_pricing_service.repository.product.ProductJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


@Sql(
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = "classpath:products.sql"
)

@Sql(
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = "classpath:discount-policy.sql"
)
@Sql(
        executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
        scripts = "classpath:cleanup-tables.sql"
)
class PricingEndpointIntegrationTests extends AbstractIntegrationTest {

    @Autowired
    DiscountPolicyJpaRepository discountPolicyJpaRepository;
    @Autowired
    ProductJpaRepository productJpaRepository;

    @Test
    void shouldCalculateTotalPriceWithAbsoluteDiscount() {
        // when
        String url = "/api/pricing/{productId}?discountPolicyId={discountPolicyId}&amount={amount}";

        Map<String, String> parameters = Map.of("amount", "11",
                "productId", "b7cc2e8b-73e2-4978-917c-746bd4649687",
                "discountPolicyId", "550e8400-e29b-41d4-a716-446655440000"
        );

        var response = restTemplate.getForEntity(url,
                TotalPriceResponse.class,
                parameters);
        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        BigDecimal expectedPrice = BigDecimal.valueOf(170.00);
        assertThat(response.getBody().totalPrice().compareTo(expectedPrice)).isEqualTo(0);
    }

    @Test
    void shouldCalculateTotalPriceWithoutDiscount() {
        // when
        String url = "/api/pricing/{productId}?&amount={amount}";

        Map<String, String> parameters = Map.of("amount", "11",
                "productId", "b7cc2e8b-73e2-4978-917c-746bd4649687"
        );

        var response = restTemplate.getForEntity(url,
                TotalPriceResponse.class,
                parameters);
        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        BigDecimal expectedPrice = BigDecimal.valueOf(220.00);
        assertThat(response.getBody().totalPrice().compareTo(expectedPrice)).isEqualTo(0);
    }

}
