package com.inpost.pricing.product_pricing_service.api.pricing;

import com.inpost.pricing.product_pricing_service.api.pricing.dto.TotalPriceResponse;
import com.inpost.pricing.product_pricing_service.service.pricing.PricingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@AllArgsConstructor
@RestController
@RequestMapping("/api/pricing")
public class PricingEndpoint {

    private final PricingService service;
    @GetMapping(path = "/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public TotalPriceResponse getTotalPrice(
            @PathVariable String productId,
            @RequestParam(required = false) String discountPolicyId,
            @RequestParam Integer amount) {

        BigDecimal totalPrice = service.calculateTotalPrice(productId, discountPolicyId, amount);
        return new TotalPriceResponse(totalPrice);
    }
}
