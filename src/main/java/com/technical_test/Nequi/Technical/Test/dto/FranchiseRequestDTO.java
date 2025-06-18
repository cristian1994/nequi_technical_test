package com.technical_test.Nequi.Technical.Test.dto;

import jakarta.validation.constraints.NotBlank;


public class FranchiseRequestDTO {

    @NotBlank(message = "{franchise.name.blank}")
    private String name;

    public FranchiseRequestDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
