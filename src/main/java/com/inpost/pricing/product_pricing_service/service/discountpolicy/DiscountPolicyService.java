package com.inpost.pricing.product_pricing_service.service.discountpolicy;

import com.inpost.pricing.product_pricing_service.repository.discountpolicy.DiscountPolicyEntity;
import com.inpost.pricing.product_pricing_service.repository.discountpolicy.DiscountPolicyJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DiscountPolicyService {

    private final DiscountPolicyJpaRepository repository;

    public DiscountPolicy create(CreateDiscountPolicy createDiscountPolicy) {
        var discountPolicyEntity = switch (createDiscountPolicy) {
            case CreateAbsoluteDiscountPolicy d ->
                    new DiscountPolicyEntity(d.getUnitThreshold(), d.getAbsoluteDiscount());
            case CreatePercentageDiscountPolicy d ->
                    new DiscountPolicyEntity(d.getUnitThreshold(), d.getPercentageDiscount());
        };

        return map(repository.save(discountPolicyEntity));
    }

    private static DiscountPolicy map(DiscountPolicyEntity savedDiscountPolicyEntity) {
        return new DiscountPolicy(savedDiscountPolicyEntity.getId().toString(),
                savedDiscountPolicyEntity.getUnitThreshold(),
                savedDiscountPolicyEntity.getAbsoluteDiscount(),
                savedDiscountPolicyEntity.getPercentageDiscount());
    }
}
