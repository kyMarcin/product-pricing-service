package com.inpost.pricing.product_pricing_service.api.discountpolicy;

import com.inpost.pricing.product_pricing_service.api.discountpolicy.dto.CreateDiscountPolicyRequest;
import com.inpost.pricing.product_pricing_service.api.discountpolicy.dto.DiscountPolicyResponse;
import com.inpost.pricing.product_pricing_service.service.discountpolicy.CreateDiscountPolicy;
import com.inpost.pricing.product_pricing_service.service.discountpolicy.DiscountPolicy;
import com.inpost.pricing.product_pricing_service.service.discountpolicy.DiscountPolicyService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/discount-policy")
public class DiscountPolicyEndpoint {

    private final DiscountPolicyService service;
    private final DiscountPolicyMapper mapper;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DiscountPolicyResponse create(@Valid @RequestBody CreateDiscountPolicyRequest request) {
        CreateDiscountPolicy createDiscountPolicy = mapper.map(request);
        DiscountPolicy discountPolicy = service.create(createDiscountPolicy);
        return mapper.map(discountPolicy);
    }
}
