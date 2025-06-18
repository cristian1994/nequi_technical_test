package com.technical_test.Nequi.Technical.Test.dto;

public class ProductHeadquarterDTO {
    private final Long id;
    private final String productName;
    private final int stock;
    private final Long headquarterId;
    private final String headquarterName;

    public ProductHeadquarterDTO(Long id, String productName, int stock, Long headquarterId, String headquarterName) {
        this.id = id;
        this.productName = productName;
        this.stock = stock;
        this.headquarterId = headquarterId;
        this.headquarterName = headquarterName;
    }

    public Long getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public int getStock() {
        return stock;
    }

    public Long getHeadquarterId() {
        return headquarterId;
    }

    public String getHeadquarterName() {
        return headquarterName;
    }
}
