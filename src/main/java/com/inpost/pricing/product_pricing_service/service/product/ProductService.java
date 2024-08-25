package com.inpost.pricing.product_pricing_service.service.product;

import com.inpost.pricing.product_pricing_service.repository.product.ProductEntity;
import com.inpost.pricing.product_pricing_service.repository.product.ProductJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ProductService {

    private final ProductJpaRepository repository;

    public Product createProduct(CreateProduct createProduct) {
        ProductEntity productEntity = repository.save(new ProductEntity(createProduct.name(), createProduct.price()));
        return map(productEntity);
    }

    private static Product map(ProductEntity productEntity) {
        return new Product(productEntity.getId().toString(), productEntity.getName(), productEntity.getPrice());
    }
}
