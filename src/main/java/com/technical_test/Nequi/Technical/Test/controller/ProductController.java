package com.technical_test.Nequi.Technical.Test.controller;

import com.technical_test.Nequi.Technical.Test.dto.ProductRequestDTO;
import com.technical_test.Nequi.Technical.Test.dto.ProductResponseDTO;
import com.technical_test.Nequi.Technical.Test.dto.ProductUpdateDTO;
import com.technical_test.Nequi.Technical.Test.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public Mono<ResponseEntity<ProductResponseDTO>> create(@Valid @RequestBody ProductRequestDTO productRequest) {
        return productService.createProduct(productRequest)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<ProductResponseDTO>> update(@Valid @RequestBody ProductUpdateDTO productUpdate, @PathVariable("id") Long id) {
        return productService.updateProduct(id, productUpdate)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{id}/stock")
    public Mono<ResponseEntity<ProductResponseDTO>> updateStock(
            @PathVariable Long id,
            @RequestParam int quantity) {
        return productService.updateStock(id, quantity)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{franchiseId}/top-products")
    public Flux<ProductResponseDTO> get(@PathVariable Long franchiseId) {
        return productService.getTopProductsByFranchise(franchiseId);
    }

}
