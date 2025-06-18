package com.technical_test.Nequi.Technical.Test.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProductUpdateDTO {
    @NotBlank(message = "{franchise.name.blank}")
    private String name;

    @NotNull(message = "{headquarters.id.notNull}")
    @Min(value = 1, message = "{headquarters.id.min}")
    private Long headquarterId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Long getHeadquarterId() {
        return headquarterId;
    }

    public void setHeadquarterId(Long headquarterId) {
        this.headquarterId = headquarterId;
    }
}
