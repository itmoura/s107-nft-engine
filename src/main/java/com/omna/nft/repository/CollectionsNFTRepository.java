package com.omna.nft.repository;

import com.omna.nft.model.entity.CollectionsNFT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface CollectionsNFTRepository extends JpaRepository<CollectionsNFT, UUID>,
        JpaSpecificationExecutor<CollectionsNFT>,
        PagingAndSortingRepository<CollectionsNFT, UUID> {

}
