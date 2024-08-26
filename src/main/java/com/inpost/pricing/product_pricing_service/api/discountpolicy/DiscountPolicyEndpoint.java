package com.inpost.pricing.product_pricing_service.api.discountpolicy;

import com.inpost.pricing.product_pricing_service.api.discountpolicy.dto.DiscountPolicyRequest;
import com.inpost.pricing.product_pricing_service.api.discountpolicy.dto.DiscountPolicyResponse;
import com.inpost.pricing.product_pricing_service.service.discountpolicy.DiscountPolicy;
import com.inpost.pricing.product_pricing_service.service.discountpolicy.DiscountPolicyCommand;
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
    public DiscountPolicyResponse create(@Valid @RequestBody DiscountPolicyRequest request) {
        DiscountPolicyCommand discountPolicyCommand = mapper.map(request);
        DiscountPolicy discountPolicy = service.create(discountPolicyCommand);
        return mapper.map(discountPolicy);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DiscountPolicyResponse update(@Valid @RequestBody DiscountPolicyRequest request, @PathVariable String id) {
        DiscountPolicyCommand discountPolicyCommand = mapper.map(request);
        DiscountPolicy discountPolicy = service.update(id, discountPolicyCommand);
        return mapper.map(discountPolicy);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
