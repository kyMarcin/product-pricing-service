package com.inpost.pricing.product_pricing_service.repository.discountpolicy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DiscountPolicyJpaRepository extends JpaRepository<DiscountPolicyEntity, UUID> {
}
