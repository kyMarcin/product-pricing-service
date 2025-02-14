package com.inpost.pricing.product_pricing_service.repository.discountpolicy;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "discount_policies")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(exclude = "id")
public class DiscountPolicyEntity {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false)
    @Setter
    private Integer unitThreshold;

    @Column
    private BigDecimal absoluteDiscount;

    @Column
    private Integer percentageDiscount;

    public DiscountPolicyEntity(Integer unitThreshold, BigDecimal absoluteDiscount) {
        this.unitThreshold = unitThreshold;
        this.absoluteDiscount = absoluteDiscount;
    }

    public DiscountPolicyEntity(Integer unitThreshold, Integer percentageDiscount) {
        this.unitThreshold = unitThreshold;
        this.percentageDiscount = percentageDiscount;
    }

    public void setAbsoluteDiscount(BigDecimal absoluteDiscount) {
        this.absoluteDiscount = absoluteDiscount;
        this.percentageDiscount = null;
    }

    public void setPercentageDiscount(Integer percentageDiscount) {
        this.percentageDiscount = percentageDiscount;
        this.absoluteDiscount = null;
    }
}
