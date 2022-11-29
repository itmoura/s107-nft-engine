package com.omna.nft.repository;

import com.omna.nft.model.entity.CollectionsNFT;
import com.omna.nft.model.entity.NFT;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface NFTRepository extends JpaRepository<NFT, UUID> {

    Page<NFT> findByCollectionsNft(CollectionsNFT collectionNFT, Pageable pageable);

    List<NFT> findAllByOwnerId(UUID ownerId);
}