package com.omna.nft.service;

import com.omna.nft.model.dto.CollectionsNFTDTO;
import com.omna.nft.model.entity.CollectionsNFT;
import com.omna.nft.repository.CollectionsNFTRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

import static java.util.Objects.nonNull;

@RequiredArgsConstructor
@Service
public class CollectionsNFTService {

    private final CollectionsNFTRepository repository;

    @Transactional
    public CollectionsNFTDTO create(CollectionsNFTDTO collectionsNFTDTO) {
        var modelMapper = new ModelMapper();
        var collectionsNFT = modelMapper.map(collectionsNFTDTO, CollectionsNFT.class);

        return modelMapper.map(repository.save(collectionsNFT), CollectionsNFTDTO.class);
    }

    @Transactional
    public CollectionsNFTDTO update(UUID id, CollectionsNFTDTO collectionsNFTDTO) {
        var collectionsOptional = repository.findById(id);

        if (collectionsOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Collection NFT not found");

        var collectionsNFT = collectionsOptional.get();

        var modelMapper = new ModelMapper();
        collectionsNFTDTO.setCollectionId(collectionsNFT.getCollectionId());
        collectionsNFTDTO.setCreated_by(collectionsNFT.getCreated_by());
        collectionsNFTDTO.setCreated_at(collectionsNFT.getCreated_at());
        modelMapper.map(collectionsNFT, collectionsNFTDTO);

        return modelMapper.map(repository.save(collectionsNFT), CollectionsNFTDTO.class);
    }

    public Page<CollectionsNFTDTO> page(String name, Pageable pageable) {
        var specification = findQuery(name);

        return repository.findAll(specification, pageable).map(CollectionsNFTDTO::convert);
    }

    private Specification<CollectionsNFT> findQuery(String search) {
        if (nonNull(search)) {
            return (root, query, criteriaBuilder) -> {
                var like = criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + search.toLowerCase() + "%");
                return criteriaBuilder.and(like);
            };
        }

        return Specification.where(null);
    }
}
