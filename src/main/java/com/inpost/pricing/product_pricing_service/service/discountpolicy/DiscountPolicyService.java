package com.inpost.pricing.product_pricing_service.service.discountpolicy;

import com.inpost.pricing.product_pricing_service.repository.discountpolicy.DiscountPolicyEntity;
import com.inpost.pricing.product_pricing_service.repository.discountpolicy.DiscountPolicyJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class DiscountPolicyService {

    private final DiscountPolicyJpaRepository repository;

    public DiscountPolicy create(DiscountPolicyCommand discountPolicyCommand) {
        var discountPolicyEntity = switch (discountPolicyCommand) {
            case AbsoluteDiscountPolicyCommand d ->
                    new DiscountPolicyEntity(d.getUnitThreshold(), d.getAbsoluteDiscount());
            case PercentageDiscountPolicyCommand d ->
                    new DiscountPolicyEntity(d.getUnitThreshold(), d.getPercentageDiscount());
        };

        return map(repository.save(discountPolicyEntity));
    }

    public DiscountPolicy update(String id, DiscountPolicyCommand discountPolicyCommand) {
        UUID uuid = UUID.fromString(id);
        DiscountPolicyEntity discountPolicy = repository.findById(uuid)
                .orElseThrow(DiscountPolicyNotFoundException::new);

        discountPolicy.setUnitThreshold(discountPolicyCommand.getUnitThreshold());
        switch (discountPolicyCommand) {
            case AbsoluteDiscountPolicyCommand d ->
                    discountPolicy.setAbsoluteDiscount(d.getAbsoluteDiscount());
            case PercentageDiscountPolicyCommand d ->
                    discountPolicy.setPercentageDiscount(d.getPercentageDiscount());
        };

        return map(repository.save(discountPolicy));
    }

    private static DiscountPolicy map(DiscountPolicyEntity savedDiscountPolicyEntity) {
        return new DiscountPolicy(savedDiscountPolicyEntity.getId().toString(),
                savedDiscountPolicyEntity.getUnitThreshold(),
                savedDiscountPolicyEntity.getAbsoluteDiscount(),
                savedDiscountPolicyEntity.getPercentageDiscount());
    }

    public void delete(String id) {
        repository.deleteById(UUID.fromString(id));
    }
}
