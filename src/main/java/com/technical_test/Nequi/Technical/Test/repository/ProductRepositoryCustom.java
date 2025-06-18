package com.technical_test.Nequi.Technical.Test.repository;

import com.technical_test.Nequi.Technical.Test.dto.ProductHeadquarterDTO;
import reactor.core.publisher.Flux;

public interface ProductRepositoryCustom {
    Flux<ProductHeadquarterDTO> findTopStockProductsByFranchise(Long franchiseId);
}
