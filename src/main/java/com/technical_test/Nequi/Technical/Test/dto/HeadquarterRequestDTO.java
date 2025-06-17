package com.technical_test.Nequi.Technical.Test.dto;

import jakarta.validation.constraints.NotNull;

public class HeadquarterRequestDTO {
    @NotNull(message = "{franchise.name.blank}")
    private String name;

    @NotNull(message = "{franchise.id.notValid}")
    private Long franchiseId;

    public HeadquarterRequestDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getFranchiseId() {
        return franchiseId;
    }

    public void setFranchiseId(Long franchiseId) {
        this.franchiseId = franchiseId;
    }
}
