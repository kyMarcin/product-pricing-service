package com.inpost.pricing.product_pricing_service.api.product;

import com.inpost.pricing.product_pricing_service.api.product.dto.CreateProductRequest;
import com.inpost.pricing.product_pricing_service.api.product.dto.ProductResponse;
import com.inpost.pricing.product_pricing_service.service.product.CreateProduct;
import com.inpost.pricing.product_pricing_service.service.product.Product;
import com.inpost.pricing.product_pricing_service.service.product.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductEndpoint {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@Valid @RequestBody CreateProductRequest request) {
        CreateProduct createProduct = productMapper.map(request);
        Product product = productService.create(createProduct);
        return productMapper.map(product);
    }
}
