package com.technical_test.Nequi.Technical.Test.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@Table("products")
public class Product {
    @Id
    private Long id;

    private String name;

    private Integer stock;

    @Column("headquarter_id")
    private Long headquarterId;
}
