package com.omna.nft.resource.v1;

import com.omna.nft.model.dto.CreateNFTDTO;
import com.omna.nft.model.dto.NFTDTO;
import com.omna.nft.model.enumeration.Status;
import com.omna.nft.service.NFTService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController("API para gerenciar NFTs")
@RequestMapping("/api/nft/v1/nfts")
@RequiredArgsConstructor
public class NFTResource {

    private final NFTService service;

    @PostMapping
    @ResponseStatus(CREATED)
    @Operation(summary = "Criação de NFTs",
            description = "Endpoint responsável por criar NFTs")
    @ApiResponse(responseCode = "201", description = "Retorna o NFTs")
    public NFTDTO create(@RequestBody CreateNFTDTO NFTDTO) {
        return service.create(NFTDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(OK)
    @Operation(summary = "Atualização de NFTs",
            description = "Endpoint responsável por atualizar NFTs")
    @ApiResponse(responseCode = "200", description = "Retorna o NFTs")
    public NFTDTO update(@PathVariable UUID id, @RequestBody CreateNFTDTO NFTDTO) {
        return service.update(id, NFTDTO);
    }

    @PutMapping("/{id}/status")
    @ResponseStatus(OK)
    @Operation(summary = "Muda o status de NFTs",
            description = "Endpoint responsável por mudar o status de NFTs")
    @ApiResponse(responseCode = "200", description = "Retorna o NFTs")
    public NFTDTO changeStatusNFT(@PathVariable UUID id, @RequestParam Status status) {
        return service.changeStatusNFT(id, status);
    }

    @PutMapping("/{id}/price")
    @ResponseStatus(OK)
    @Operation(summary = "Muda o preço de NFTs",
            description = "Endpoint responsável por mudar o preço de NFTs")
    @ApiResponse(responseCode = "200", description = "Retorna o NFTs")
    public NFTDTO changePriceNFT(@PathVariable UUID id, @RequestParam BigDecimal price) {
        return service.changePriceNFT(id, price);
    }

    @GetMapping("/collection/{id}")
    @ResponseStatus(OK)
    @Operation(summary = "Busca NFTs por coleção",
            description = "Endpoint responsável por buscar NFTs por coleção")
    @ApiResponse(responseCode = "200", description = "Retorna o NFTs")
    public Page<NFTDTO> findByCollection(@PathVariable UUID id, Pageable pageable) {
        return service.findByCollection(id, pageable);
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    @Operation(summary = "Visualização de NFTs",
            description = "Endpoint responsável por buscar NFTs por id")
    @ApiResponse(responseCode = "200", description = "Retorna o NFTs")
    public NFTDTO findById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @GetMapping
    @ResponseStatus(OK)
    @Operation(summary = "Listagem de NFTs",
            description = "Endpoint responsável por listar NFTs")
    @ApiResponse(responseCode = "200", description = "Retorna o NFTs")
    public Page<NFTDTO> findAll(Pageable pageable) {
        return service.findAll(pageable);
    }

    @GetMapping("/me/{ownerId}")
    @ResponseStatus(OK)
    @Operation(summary = "Listagem de NFTs",
            description = "Endpoint responsável por listar NFTs")
    @ApiResponse(responseCode = "200", description = "Retorna o NFTs")
    public List<NFTDTO> findAllByOwnerId(@PathVariable UUID ownerId) {
        return service.findAllByOwnerId(ownerId);
    }

    @PostMapping("/{id}/buy")
    @ResponseStatus(OK)
    @Operation(summary = "Compra de NFTs",
            description = "Endpoint responsável por comprar NFTs")
    @ApiResponse(responseCode = "200", description = "Retorna o NFTs")
    public NFTDTO buy(@PathVariable UUID id, @RequestParam UUID newOwner) {
        return service.buy(id, newOwner);
    }
}
