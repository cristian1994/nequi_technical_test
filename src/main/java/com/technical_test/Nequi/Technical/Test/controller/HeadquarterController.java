package com.technical_test.Nequi.Technical.Test.controller;

import com.technical_test.Nequi.Technical.Test.dto.HeadquarterRequestDTO;
import com.technical_test.Nequi.Technical.Test.dto.HeadquarterResponseDTO;
import com.technical_test.Nequi.Technical.Test.service.HeadquarterService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/headquarters")
public class HeadquarterController {
    private final HeadquarterService headquarterService;

    public HeadquarterController(HeadquarterService headquarterService) {
        this.headquarterService = headquarterService;
    }

    @PostMapping
    public Mono<ResponseEntity<HeadquarterResponseDTO>> create(@Valid @RequestBody HeadquarterRequestDTO headquarterRequest) {
        return headquarterService.createHeadquarter(headquarterRequest)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<HeadquarterResponseDTO>> update(@Valid @RequestBody HeadquarterRequestDTO headquarterRequest, @PathVariable("id") Long id) {
        return headquarterService.updateHeadquarter(id, headquarterRequest)
                .map(ResponseEntity::ok);
    }
}
