package com.technical_test.Nequi.Technical.Test.controller;

import com.technical_test.Nequi.Technical.Test.dto.FranchiseRequestDTO;
import com.technical_test.Nequi.Technical.Test.dto.FranchiseResponseDTO;
import com.technical_test.Nequi.Technical.Test.service.FranchiseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/franchises")
public class FranchiseController {

    private final FranchiseService service;

    public FranchiseController(FranchiseService service) {
        this.service = service;
    }

    @PostMapping
    public Mono<ResponseEntity<FranchiseResponseDTO>> create(@Valid @RequestBody FranchiseRequestDTO franchiseRequest) {
        return service.createFranchise(franchiseRequest)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<FranchiseResponseDTO>> create(@Valid @RequestBody FranchiseRequestDTO franchiseRequest, @PathVariable("id") Long id) {
        return service.updateFranchise(id, franchiseRequest)
                .map(ResponseEntity::ok);
    }


}
