package com.technical_test.Nequi.Technical.Test.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class ProductRequestDTO {
    @NotNull(message = "{franchise.name.blank}")
    private String name;

    @Min(value = 0, message = "{product.stock.noValid}")
    private int stock;

    @NotNull(message = "{headquarters.id.notValid}")
    private Long headquarterId;

}
