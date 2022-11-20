package com.omna.nft.resource.v1;

import com.omna.nft.model.dto.CollectionsNFTDTO;
import com.omna.nft.service.CollectionsNFTService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController("API para gerenciar coleções de NFTs")
@RequestMapping("/api/nft/v1/collections")
@RequiredArgsConstructor
public class CollectionsNFTResource {

    private final CollectionsNFTService service;

    @PostMapping
    @ResponseStatus(CREATED)
    @Operation(summary = "Criação de uma coleção de NFTs",
            description = "Endpoint responsável por criar uma coleção de NFTs")
    @ApiResponse(responseCode = "201", description = "Retorna a coleção de NFTs")
    public CollectionsNFTDTO create(@RequestBody CollectionsNFTDTO collectionsNFTDTO) {
        return service.create(collectionsNFTDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(OK)
    @Operation(summary = "Atualização de uma coleção de NFTs",
            description = "Endpoint responsável por atualizar uma coleção de NFTs")
    @ApiResponse(responseCode = "200", description = "Retorna a coleção de NFTs")
    public CollectionsNFTDTO update(@PathVariable UUID id, @RequestBody CollectionsNFTDTO collectionsNFTDTO) {
        return service.update(id, collectionsNFTDTO);
    }

    @GetMapping
    @ResponseStatus(OK)
    @Operation(summary = "Listagem de coleções de NFTs",
            description = "Endpoint responsável por listar coleções de NFTs")
    @ApiResponse(responseCode = "200", description = "Retorna a lista de coleções de NFTs")
    public Page<CollectionsNFTDTO> page(@RequestParam(required = false) String name, Pageable pageable) {
        return service.page(name, pageable);
    }
}
