package com.inpost.pricing.product_pricing_service.api.product;

import com.inpost.pricing.product_pricing_service.api.product.dto.CreateProductRequest;
import com.inpost.pricing.product_pricing_service.api.product.dto.ProductResponse;
import com.inpost.pricing.product_pricing_service.service.product.CreateProduct;
import com.inpost.pricing.product_pricing_service.service.product.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public CreateProduct map(CreateProductRequest request) {
        return new CreateProduct(request.name(), request.price());
    }

    public ProductResponse map(Product product) {
        return new ProductResponse(product.id(), product.name(), product.price());
    }
}
