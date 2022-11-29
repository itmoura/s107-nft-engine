package com.omna.nft.service;

import com.omna.nft.model.dto.CreateNFTDTO;
import com.omna.nft.model.dto.NFTDTO;
import com.omna.nft.model.entity.NFT;
import com.omna.nft.repository.CollectionsNFTRepository;
import com.omna.nft.repository.NFTRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class NFTService {

    private final NFTRepository repository;

    private final CollectionsNFTRepository collectionsNFTRepository;

    @Transactional
    public NFTDTO create(CreateNFTDTO NFTDTO) {
        var collectionNFT = collectionsNFTRepository.findById(NFTDTO.getCollectionId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Collection NFT not found"));

        var modelMapper = new ModelMapper();
        var collectionsNFT = modelMapper.map(NFTDTO, NFT.class);
        collectionsNFT.setCollectionsNft(collectionNFT);

        return modelMapper.map(repository.save(collectionsNFT), NFTDTO.class);
    }

    @Transactional
    public NFTDTO update(UUID id, CreateNFTDTO nftdto) {
        var nft = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "NFT not found"));

        var modelMapper = new ModelMapper();
        nftdto.setCollectionId(nft.getCollectionsNft().getCollectionId());
        nftdto.setCreated_by(nft.getCreated_by());
        nftdto.setCreated_at(nft.getCreated_at());
        modelMapper.map(nft, nftdto);

        return modelMapper.map(repository.save(nft), NFTDTO.class);
    }

    public Page<NFTDTO> findByCollection(UUID id, Pageable pageable) {
        var collectionNFT = collectionsNFTRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Collection NFT not found"));

        var modelMapper = new ModelMapper();
        var find = repository.findByCollectionsNft(collectionNFT, pageable);
        return new PageImpl<>(find.map(nft -> modelMapper.map(nft, NFTDTO.class)).getContent(), pageable, find.getTotalElements());
    }

    public NFTDTO findById(UUID id) {
        var modelMapper = new ModelMapper();
        return modelMapper.map(repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "NFT not found")), NFTDTO.class);
    }

    public Page<NFTDTO> findAll(Pageable pageable) {
        var modelMapper = new ModelMapper();
        var find = repository.findAll(pageable);
        return new PageImpl<>(find.map(nft -> modelMapper.map(nft, NFTDTO.class)).getContent(), pageable, find.getTotalElements());
    }

    public NFTDTO buy(UUID id, UUID newOwner) {
        var modelMapper = new ModelMapper();
        var nft = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "NFT not found"));

        nft.setOwner_id(newOwner);
        return modelMapper.map(repository.save(nft), NFTDTO.class);
    }
}
