package com.technical_test.Nequi.Technical.Test.service;

import com.technical_test.Nequi.Technical.Test.dto.FranchiseRequestDTO;
import com.technical_test.Nequi.Technical.Test.dto.FranchiseResponseDTO;
import com.technical_test.Nequi.Technical.Test.model.Franchise;
import com.technical_test.Nequi.Technical.Test.repository.FranchiseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Service
public class FranchiseService {

    private final FranchiseRepository franchiseRepository;
    private final MessageService messageService;

    public FranchiseService(FranchiseRepository franchiseRepository, MessageService messageService) {
        this.franchiseRepository = franchiseRepository;
        this.messageService = messageService;
    }

    public Mono<FranchiseResponseDTO> createFranchise(FranchiseRequestDTO franchiseRequest) {
        Franchise franchise = new Franchise();
        franchise.setName(franchiseRequest.getName());
        return franchiseRepository.save(franchise)
                .map(saved -> new FranchiseResponseDTO(saved.getId(), saved.getName()));
    }

    public Mono<FranchiseResponseDTO> updateFranchise(Long id, FranchiseRequestDTO franchiseRequest) {
        return validateById(id).flatMap(franchise -> {
            franchise.setName(franchiseRequest.getName());
            return franchiseRepository.save(franchise);
        }).map(updated -> new FranchiseResponseDTO(updated.getId(), updated.getName()));
    }

    public Mono<Franchise> validateById(Long id) {
        return franchiseRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, messageService.getMessage("franchise.id.notValid"))));
    }
}
