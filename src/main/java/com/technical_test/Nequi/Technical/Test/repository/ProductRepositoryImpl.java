package com.technical_test.Nequi.Technical.Test.repository;

import com.technical_test.Nequi.Technical.Test.dto.ProductHeadquarterDTO;
import org.springframework.r2dbc.core.DatabaseClient;
import reactor.core.publisher.Flux;

public class ProductRepositoryImpl implements ProductRepositoryCustom {

    private final DatabaseClient databaseClient;

    public ProductRepositoryImpl(DatabaseClient databaseClient) {
        this.databaseClient = databaseClient;
    }


    @Override
    public Flux<ProductHeadquarterDTO> findTopStockProductsByFranchise(Long franchiseId) {
        String sql = """
                    SELECT p.id AS id,
                           p.name AS name,
                           p.stock AS stock,
                           h.id AS headquarter_id,
                           h.name AS headquarter_name
                    FROM headquarters h
                    JOIN (
                        SELECT DISTINCT ON (headquarter_id) *
                        FROM products
                        ORDER BY headquarter_id, stock DESC
                    ) p ON p.headquarter_id = h.id
                    WHERE h.franchise_id = :franchiseId
                """;

        return databaseClient.sql(sql)
                .bind("franchiseId", franchiseId)
                .map((row, metadata) -> new ProductHeadquarterDTO(
                        row.get("id", Long.class),
                        row.get("name", String.class),
                        row.get("stock", Integer.class),
                        row.get("headquarter_id", Long.class),
                        row.get("headquarter_name", String.class)
                ))
                .all();
    }
}
