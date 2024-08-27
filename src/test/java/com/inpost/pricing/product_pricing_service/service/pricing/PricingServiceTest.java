package com.inpost.pricing.product_pricing_service.service.pricing;

import com.inpost.pricing.product_pricing_service.service.discountpolicy.DiscountPolicyService;
import com.inpost.pricing.product_pricing_service.service.product.Product;
import com.inpost.pricing.product_pricing_service.service.product.ProductService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PricingServiceTest {


    private final DiscountPolicyService discountPolicyService = mock(DiscountPolicyService.class);
    private final ProductService productService = mock(ProductService.class);
    private final PricingService sut = new PricingService(productService, discountPolicyService);

    @Test
    public void shouldThrowExceptionWhenTotalPriceIsBelow0() {
        //given
        String productId = "productId";
        Product productMock = mock(Product.class);
        when(productMock.price()).thenReturn(BigDecimal.TEN);
        when(productService.get(productId)).thenReturn(productMock);


        //when & then
        assertThrows(TotalPriceBelowZeroException.class, () -> {
            sut.calculateTotalPrice(productId, null, 0);
        });
    }
}