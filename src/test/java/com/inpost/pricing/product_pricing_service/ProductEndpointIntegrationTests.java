package com.inpost.pricing.product_pricing_service;

import com.inpost.pricing.product_pricing_service.api.discountpolicy.dto.DiscountPolicyResponse;
import com.inpost.pricing.product_pricing_service.api.product.dto.CreateProductRequest;
import com.inpost.pricing.product_pricing_service.api.product.dto.ProductResponse;
import com.inpost.pricing.product_pricing_service.repository.product.ProductJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ProductEndpointIntegrationTests extends AbstractIntegrationTest {

    @Autowired
    ProductJpaRepository repository;

    @Test
    void shouldReturn201WhenCreateProductRequestIsValid() {
        // given
        var createProduct = new CreateProductRequest("first product", BigDecimal.TEN);
        // when
        var response = postForEntity("/api/products", createProduct, ProductResponse.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().id()).isNotEmpty();
        assertThat(response.getBody().name()).isEqualTo("first product");
        assertThat(response.getBody().price()).isEqualTo(BigDecimal.TEN);
    }

    @ParameterizedTest
    @MethodSource("invalidCreateProductBody")
    void shouldReturn400ForInvalidPayload(CreateProductRequest product) {
        // when
        var response = postForEntity("/api/products", product, DiscountPolicyResponse.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    private static Stream<CreateProductRequest> invalidCreateProductBody() {
        return Stream.of(
                new CreateProductRequest(null, BigDecimal.TEN),
                new CreateProductRequest("valid name", null),
                new CreateProductRequest("valid name", BigDecimal.ZERO)
        );
    }

}
