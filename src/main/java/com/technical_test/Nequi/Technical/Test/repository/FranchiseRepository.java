package com.technical_test.Nequi.Technical.Test.repository;

import com.technical_test.Nequi.Technical.Test.model.Franchise;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FranchiseRepository extends ReactiveCrudRepository<Franchise, Long> {
}
