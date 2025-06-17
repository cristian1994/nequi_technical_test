package com.technical_test.Nequi.Technical.Test.dto;

public class HeadquarterResponseDTO {
    private Long id;
    private String name;

    public HeadquarterResponseDTO() {
    }

    public HeadquarterResponseDTO(Long id, String name) {
        this.id = id;
        this.name = name;
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
}
