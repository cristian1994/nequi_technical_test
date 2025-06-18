package com.technical_test.Nequi.Technical.Test.dto;

public class ProductResponseDTO {
    private Long id;
    private String name;
    private int stock;
    private HeadquarterResponseDTO headquarter;

    public ProductResponseDTO(Long id, String name, int stock) {
        this.id = id;
        this.name = name;
        this.stock = stock;
    }

    public ProductResponseDTO(Long id, String name, int stock, HeadquarterResponseDTO headquarter) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.headquarter = headquarter;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public HeadquarterResponseDTO getHeadquarter() {
        return headquarter;
    }

    public void setHeadquarter(HeadquarterResponseDTO headquarter) {
        this.headquarter = headquarter;
    }
}
