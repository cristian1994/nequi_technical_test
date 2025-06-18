package com.technical_test.Nequi.Technical.Test.service;

import com.technical_test.Nequi.Technical.Test.dto.*;
import com.technical_test.Nequi.Technical.Test.model.Headquarter;
import com.technical_test.Nequi.Technical.Test.model.Product;
import com.technical_test.Nequi.Technical.Test.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final HeadquarterService headquarterService;
    private final FranchiseService franchiseService;
    private final MessageService messageService;

    public ProductService(ProductRepository productRepository, HeadquarterService headquarterService, FranchiseService franchiseService, MessageService messageService) {
        this.productRepository = productRepository;
        this.headquarterService = headquarterService;
        this.franchiseService = franchiseService;
        this.messageService = messageService;
    }

    public Mono<ProductResponseDTO> createProduct(ProductRequestDTO productRequest) {
        return headquarterService.validateById(productRequest.getHeadquarterId()).flatMap(headquarter -> {
            Product product = new Product();
            product.setName(productRequest.getName());
            product.setStock(productRequest.getStock());
            product.setHeadquarterId(headquarter.getId());
            return productRepository.save(product).map(saved -> new ProductResponseDTO(saved.getId(), saved.getName(), saved.getStock()));
        });
    }

    public Mono<ProductResponseDTO> updateProduct(Long id, ProductUpdateDTO productUpdate) {
        return productRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        messageService.getMessage("product.id.notValid")
                )))
                .flatMap(product -> headquarterService.validateById(productUpdate.getHeadquarterId())
                        .flatMap(headquarter -> {
                            product.setName(productUpdate.getName());
                            product.setHeadquarterId(headquarter.getId());
                            return productRepository.save(product);
                        }))
                .map(updated -> new ProductResponseDTO(updated.getId(), updated.getName(), updated.getStock()));
    }

    public Mono<ProductResponseDTO> updateStock(Long productId, int quantityChange) {
        return productRepository.findById(productId)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, messageService.getMessage("product.id.notValid"))))
                .flatMap(product -> {
                    int newStock = product.getStock() + quantityChange;
                    if (newStock < 0) {
                        return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, messageService.getMessage("product.stock.insufficient")));
                    }
                    product.setStock(newStock);
                    return productRepository.save(product);
                }).map(saved -> new ProductResponseDTO(saved.getId(), saved.getName(), saved.getStock()));
    }

    public Flux<ProductResponseDTO> getTopProductsByFranchise(Long franchiseId) {
        return productRepository.findTopStockProductsByFranchise(franchiseId)
                .map(row -> new ProductResponseDTO(
                        row.getId(),
                        row.getProductName(),
                        row.getStock(),
                        new HeadquarterResponseDTO(row.getHeadquarterId(), row.getHeadquarterName())
                ));
    }
}
