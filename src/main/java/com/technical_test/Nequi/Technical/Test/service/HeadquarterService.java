package com.technical_test.Nequi.Technical.Test.service;

import com.technical_test.Nequi.Technical.Test.dto.HeadquarterRequestDTO;
import com.technical_test.Nequi.Technical.Test.dto.HeadquarterResponseDTO;
import com.technical_test.Nequi.Technical.Test.model.Headquarter;
import com.technical_test.Nequi.Technical.Test.repository.HeadquarterRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Service
public class HeadquarterService {
    private final HeadquarterRepository headquarterRepository;
    private final FranchiseService franchiseService;
    private final MessageService messageService;

    public HeadquarterService(HeadquarterRepository headquarterRepository, FranchiseService franchiseService, MessageService messageService) {
        this.headquarterRepository = headquarterRepository;
        this.franchiseService = franchiseService;
        this.messageService = messageService;
    }

    public Mono<HeadquarterResponseDTO> createHeadquarter(HeadquarterRequestDTO headquarterRequest) {
        return franchiseService.validateById(headquarterRequest.getFranchiseId()).flatMap(franchise -> {
            Headquarter headquarter = new Headquarter();
            headquarter.setName(headquarterRequest.getName());
            headquarter.setFranchiseId(franchise.getId());
            return headquarterRepository.save(headquarter).map(saved -> new HeadquarterResponseDTO(saved.getId(), saved.getName()));
        });
    }

    public Mono<HeadquarterResponseDTO> updateHeadquarter(Long id, HeadquarterRequestDTO headquarterRequest) {
        return headquarterRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        messageService.getMessage("headquarter.id.notValid")
                )))
                .flatMap(headquarter -> franchiseService.validateById(headquarterRequest.getFranchiseId())
                        .flatMap(franchise -> {
                            headquarter.setName(headquarterRequest.getName());
                            headquarter.setFranchiseId(franchise.getId());
                            return headquarterRepository.save(headquarter);
                        }))
                .map(updated -> new HeadquarterResponseDTO(updated.getId(), updated.getName()));
    }
}
