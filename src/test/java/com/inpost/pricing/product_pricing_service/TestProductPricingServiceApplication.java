package com.inpost.pricing.product_pricing_service;

import org.springframework.boot.SpringApplication;

public class TestProductPricingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(ProductPricingServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
