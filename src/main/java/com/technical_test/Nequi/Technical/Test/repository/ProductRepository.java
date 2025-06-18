package com.technical_test.Nequi.Technical.Test.repository;

import com.technical_test.Nequi.Technical.Test.model.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends ReactiveCrudRepository<Product, Long>, ProductRepositoryCustom {
}
