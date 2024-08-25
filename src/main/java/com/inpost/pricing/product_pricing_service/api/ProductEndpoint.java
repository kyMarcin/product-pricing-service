package com.inpost.pricing.product_pricing_service.api;

import com.inpost.pricing.product_pricing_service.api.dto.CreateProductRequest;
import com.inpost.pricing.product_pricing_service.api.dto.ProductResponse;
import com.inpost.pricing.product_pricing_service.service.CreateProduct;
import com.inpost.pricing.product_pricing_service.service.Product;
import com.inpost.pricing.product_pricing_service.service.ProductService;
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
        Product product = productService.createProduct(createProduct);
        return productMapper.map(product);
    }
}
